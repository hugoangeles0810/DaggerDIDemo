package io.github.hugoangeles0810.daggerdidemo.domain.interactor.implementations


import io.github.hugoangeles0810.daggerdidemo.domain.executor.InteractorExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.MainThreadExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.BaseCallback
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.BaseInteractor


abstract class AbstractInteractor<T : BaseCallback, U : Any>(val interactorExecutor: InteractorExecutor, val mainThreadExecutor: MainThreadExecutor) : Runnable, BaseInteractor<T, U> {

    protected lateinit var mCallback: T
    protected lateinit var mParams: U

    override fun execute(callback: T, params: U?) {
        mCallback = callback
        if (params != null)
            mParams = params
        interactorExecutor.run(this@AbstractInteractor)
    }
}