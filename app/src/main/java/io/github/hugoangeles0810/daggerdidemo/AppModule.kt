package io.github.hugoangeles0810.daggerdidemo

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.hugoangeles0810.daggerdidemo.data.di.ApiModule
import io.github.hugoangeles0810.daggerdidemo.data.di.DataModule
import io.github.hugoangeles0810.daggerdidemo.domain.executor.InteractorExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.MainThreadExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations.MainThreadExecutorImpl
import io.github.hugoangeles0810.daggerdidemo.domain.executor.implementations.ThreadExecutor
import javax.inject.Singleton

@Module(includes = arrayOf(DataModule::class, ApiModule::class))
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun providesContext(): Context = app

    @Singleton
    @Provides
    fun providesApplication(): App = app

    @Singleton
    @Provides
    fun providesInteractorExecutor(): InteractorExecutor {
        return ThreadExecutor()
    }

    @Singleton
    @Provides
    fun providesMainThreadExecutor(): MainThreadExecutor {
        return MainThreadExecutorImpl()
    }

}