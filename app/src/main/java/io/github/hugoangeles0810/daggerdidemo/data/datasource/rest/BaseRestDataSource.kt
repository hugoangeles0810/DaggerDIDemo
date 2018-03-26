package io.github.hugoangeles0810.daggerdidemo.data.datasource.rest

import io.github.hugoangeles0810.daggerdidemo.data.exceptions.NetworkException
import io.github.hugoangeles0810.daggerdidemo.data.exceptions.ServerException
import java.io.IOException
import java.net.HttpURLConnection

open class BaseRestDataStore {

    protected fun <T> parseResult(callback: () -> retrofit2.Response<T>): T? {
        try {
            val response = callback()
            when (response.code()) {
                HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_CREATED -> return response.body()
                else -> throw ServerException()
            }
        } catch (iex: IOException) {
            iex.printStackTrace()
            throw NetworkException()
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ServerException()
        }
    }
}