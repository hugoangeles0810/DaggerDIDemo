package io.github.hugoangeles0810.daggerdidemo.data.datasource

import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie

interface MovieDataSource {

    fun list(): List<Movie>

}