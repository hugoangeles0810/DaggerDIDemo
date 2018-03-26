package io.github.hugoangeles0810.daggerdidemo.presentation.common

interface BasePresenter<in T : BaseView> {

    fun attach(view: T)
    fun detach()
}