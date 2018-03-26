package io.github.hugoangeles0810.daggerdidemo.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.github.hugoangeles0810.daggerdidemo.R
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import io.github.hugoangeles0810.daggerdidemo.presentation.common.component
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private var snackbar: Snackbar? = null
    private val adapter: RVMoviesAdapter by lazy { RVMoviesAdapter() }

    @Inject
    lateinit var presenter: MainPresenter

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.injectTo(this)
        presenter.attach(this)
        presenter.listMovies()

        rvMovies.adapter = adapter
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
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