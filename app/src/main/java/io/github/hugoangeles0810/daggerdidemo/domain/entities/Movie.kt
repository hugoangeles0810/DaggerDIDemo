package io.github.hugoangeles0810.daggerdidemo.domain.entities

data class Movie(
        val id: String,
        val title: String,
        val overview: String,
        val popularity: Double,
        val posterUrl: String?,
        val backdropUrl: String?
)