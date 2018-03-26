package io.github.hugoangeles0810.daggerdidemo

import dagger.Component
import io.github.hugoangeles0810.daggerdidemo.data.di.ApiModule
import io.github.hugoangeles0810.daggerdidemo.data.di.DataModule
import io.github.hugoangeles0810.daggerdidemo.domain.di.DomainModule
import io.github.hugoangeles0810.daggerdidemo.presentation.di.PresentationModule
import io.github.hugoangeles0810.daggerdidemo.presentation.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class, ApiModule::class, PresentationModule::class, DomainModule::class))
interface AppComponent {

    fun injectTo(activity: MainActivity)
}