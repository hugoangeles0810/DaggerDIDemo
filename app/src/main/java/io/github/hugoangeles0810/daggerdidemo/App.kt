package io.github.hugoangeles0810.daggerdidemo

import android.app.Application
import io.github.hugoangeles0810.daggerdidemo.data.di.ApiModule

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule())
                .build()
    }
}