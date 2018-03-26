package io.github.hugoangeles0810.daggerdidemo.domain.executor

interface MainThreadExecutor {
    fun execute(runnable: Runnable)
}