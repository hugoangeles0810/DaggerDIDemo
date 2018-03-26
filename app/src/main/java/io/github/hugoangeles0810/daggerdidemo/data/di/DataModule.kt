package io.github.hugoangeles0810.daggerdidemo.data.di

import dagger.Module
import dagger.Provides
import io.github.hugoangeles0810.daggerdidemo.data.datasource.ConfigurationDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.MovieDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.ConfigurationRestDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.MovieRestDataSource

@Module
class DataModule {

    @Provides
    fun providesMoviesDataSource(impl: MovieRestDataSource): MovieDataSource = impl

    @Provides
    fun providesConfigurationDataSource(impl: ConfigurationRestDataSource): ConfigurationDataSource = impl
}