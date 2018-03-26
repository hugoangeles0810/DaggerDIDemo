package io.github.hugoangeles0810.daggerdidemo.presentation.di

import dagger.Module
import dagger.Provides
import io.github.hugoangeles0810.daggerdidemo.presentation.main.MainPresenter
import io.github.hugoangeles0810.daggerdidemo.presentation.main.MainPresenterImpl

@Module
class PresentationModule {

    @Provides
    fun providesMainPresenter(impl: MainPresenterImpl): MainPresenter = impl
}