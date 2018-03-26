package io.github.hugoangeles0810.daggerdidemo.domain.entities

data class Configuration(
    private val baseUrl: String,
    private val posterSize: String,
    private val backdropSize: String
) {

    fun getPosterUrl(path: String): String = baseUrl + posterSize + path

    fun getBackdropUrl(path: String): String = baseUrl + backdropSize + path
}