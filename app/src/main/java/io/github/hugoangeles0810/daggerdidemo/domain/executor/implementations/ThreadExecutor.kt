package io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations

import io.github.hugoangeles0810.daggerdidemo.domain.executor.InteractorExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadExecutor : InteractorExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        threadPoolExecutor = ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME.toLong(), TIME_UNIT, WORK_QUEUE)
    }

    override fun run(interactor: Runnable?) {
        if (interactor != null) {
            threadPoolExecutor.submit(interactor)
        }
    }

    companion object {
        private val CORE_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 5
        private val KEEP_ALIVE_TIME = 120
        private val TIME_UNIT = TimeUnit.SECONDS
        private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()
    }
}