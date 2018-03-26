package io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations

import android.os.Handler
import android.os.Looper

import io.github.hugoangeles0810.daggerdidemo.domain.executor.MainThreadExecutor

class MainThreadExecutorImpl : MainThreadExecutor {

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable) {
        handler.post(runnable)
    }
}
