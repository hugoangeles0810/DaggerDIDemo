package io.github.hugoangeles0810.daggerdidemo.presentation.main

import io.github.hugoangeles0810.daggerdidemo.presentation.common.BasePresenter

interface MainPresenter : BasePresenter<MainView> {

    fun listMovies()
}