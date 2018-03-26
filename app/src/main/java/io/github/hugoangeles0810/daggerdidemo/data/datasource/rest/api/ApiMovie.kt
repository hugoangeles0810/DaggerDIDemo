package io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api

import io.github.hugoangeles0810.daggerdidemo.data.model.rest.DiscoverMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiMovie {

    @GET("discover/movie")
    fun listMovies(): Call<DiscoverMoviesResponse>
}