package io.github.hugoangeles0810.daggerdidemo.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.hugoangeles0810.daggerdidemo.BuildConfig
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiConfiguration
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiMovie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class ApiModule {

    companion object {
        private const val API_KEY_QUERY = "api_key"
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return interceptor
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val url = it.request().url()
                            .newBuilder()
                            .addQueryParameter(API_KEY_QUERY, BuildConfig.API_KEY)
                            .build()
                    val newRequest = it.request().newBuilder().url(url).build()
                    it.proceed(newRequest)
                }
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Singleton
    @Provides
    open fun providesApiUrl(): String = BuildConfig.API_URL

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson, apiUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    fun providesApiMovie(retrofit: Retrofit): ApiMovie = retrofit.create(ApiMovie::class.java)

    @Singleton
    @Provides
    fun providesApiConfiguration(retrofit: Retrofit): ApiConfiguration = retrofit.create(ApiConfiguration::class.java)
}