package io.github.hugoangeles0810.daggerdidemo.domain.interactor

import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie

interface ListMoviesInteractor : BaseInteractor<ListMoviesInteractor.Callback, Any> {

    interface Callback : BaseCallback {
        fun onSuccess(movies: List<Movie>)
    }
}