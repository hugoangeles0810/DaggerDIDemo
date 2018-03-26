package io.github.hugoangeles0810.daggerdidemo.domain.di

import dagger.Module
import dagger.Provides
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.ListMoviesInteractor
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.implementations.ListMoviesInteractorImpl

@Module
class DomainModule {

    @Provides
    fun providesListMoviesInteractor(impl: ListMoviesInteractorImpl): ListMoviesInteractor = impl
}