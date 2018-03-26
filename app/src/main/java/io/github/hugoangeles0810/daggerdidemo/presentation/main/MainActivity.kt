package io.github.hugoangeles0810.daggerdidemo.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.github.hugoangeles0810.daggerdidemo.BuildConfig
import io.github.hugoangeles0810.daggerdidemo.R
import io.github.hugoangeles0810.daggerdidemo.data.datasource.ConfigurationDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.MovieDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.ConfigurationRestDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.MovieRestDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiConfiguration
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiMovie
import io.github.hugoangeles0810.daggerdidemo.data.repositories.MovieRepository
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import io.github.hugoangeles0810.daggerdidemo.domain.executor.InteractorExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.MainThreadExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations.MainThreadExecutorImpl
import io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations.ThreadExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.ListMoviesInteractor
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.implementations.ListMoviesInteractorImpl
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MainView {

    private var snackbar: Snackbar? = null
    private val adapter: RVMoviesAdapter by lazy { RVMoviesAdapter() }

    private lateinit var presenter: MainPresenter

    companion object {
        private const val API_KEY_QUERY = "api_key"

        fun newInstance(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Solo para explicar como es que se crearan las dependencias
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val url = it.request().url()
                            .newBuilder()
                            .addQueryParameter(API_KEY_QUERY, BuildConfig.API_KEY)
                            .build()
                    val newRequest = it.request().newBuilder().url(url).build()
                    it.proceed(newRequest)
                }
                .addInterceptor(loggingInterceptor)
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiMovies = retrofit.create(ApiMovie::class.java)

        val apiConfiguration = retrofit.create(ApiConfiguration::class.java)

        val interactorExecutor: InteractorExecutor = ThreadExecutor()
        val mainThredExecutor: MainThreadExecutor = MainThreadExecutorImpl()

        val movieDataSource: MovieDataSource = MovieRestDataSource(apiMovies)
        val configurationDataSource: ConfigurationDataSource = ConfigurationRestDataSource(apiConfiguration)

        val movieRepository = MovieRepository(movieDataSource, configurationDataSource)

        val listMoviesInteractor: ListMoviesInteractor = ListMoviesInteractorImpl(interactorExecutor, mainThredExecutor, movieRepository)

        presenter = MainPresenterImpl(listMoviesInteractor)
        presenter.attach(this)
        presenter.listMovies()

        rvMovies.adapter = adapter
    }

    override fun showError(message: String) {
        snackbar?.dismiss()
        snackbar = Snackbar.make(container, message, Snackbar.LENGTH_LONG)
        snackbar?.show()
    }

    override fun showError(message: Int) {
        showError(getString(message))
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMovies(movies: List<Movie>) {
        adapter.data = movies
    }
}