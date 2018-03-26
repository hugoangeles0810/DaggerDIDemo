package io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api

import io.github.hugoangeles0810.daggerdidemo.data.model.rest.Configuration
import retrofit2.Call
import retrofit2.http.GET

interface ApiConfiguration {

    @GET("configuration")
    fun getConfiguration(): Call<Configuration>
}