package io.github.hugoangeles0810.daggerdidemo.presentation.main

import io.github.hugoangeles0810.daggerdidemo.R
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.ListMoviesInteractor
import javax.inject.Inject

class MainPresenterImpl
    @Inject constructor(private val listMoviesInteractor: ListMoviesInteractor) : MainPresenter {

    private var mView: MainView? = null

    override fun attach(view: MainView) {
        mView = view
    }

    override fun detach() {
        mView = null
    }

    override fun listMovies() {
        mView?.showProgress(true)
        listMoviesInteractor.execute(object: ListMoviesInteractor.Callback {
            override fun onSuccess(movies: List<Movie>) {
                if (mView == null || mView!!.isFinishing()) return

                mView!!.showMovies(movies)
                mView!!.showProgress(false)
            }

            override fun onNetworkError() {
                if (mView == null || mView!!.isFinishing()) return

                mView!!.showError(R.string.common_error_network)
                mView!!.showProgress(false)
            }

            override fun onError(error: String) {
                if (mView == null || mView!!.isFinishing()) return

                mView!!.showError(R.string.common_error_server)
                mView!!.showProgress(false)
            }
        })
    }
}