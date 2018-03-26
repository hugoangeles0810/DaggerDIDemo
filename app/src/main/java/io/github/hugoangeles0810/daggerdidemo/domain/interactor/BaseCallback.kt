package io.github.hugoangeles0810.daggerdidemo.domain.interactor

interface BaseCallback {
    fun onNetworkError()

    fun onError(error: String)
}