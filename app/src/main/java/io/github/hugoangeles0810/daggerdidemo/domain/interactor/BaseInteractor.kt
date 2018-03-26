package io.github.hugoangeles0810.daggerdidemo.domain.interactor

interface BaseInteractor<in T : BaseCallback, in U> {
    fun execute(callback: T, params: U? = null)
}