package io.github.hugoangeles0810.daggerdidemo.presentation.main

import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import io.github.hugoangeles0810.daggerdidemo.presentation.common.BaseView

interface MainView : BaseView {

    fun showProgress(show: Boolean)
    fun showMovies(movies: List<Movie>)
}