package io.github.hugoangeles0810.daggerdidemo.data.datasource.rest

import io.github.hugoangeles0810.daggerdidemo.data.datasource.MovieDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiMovie
import io.github.hugoangeles0810.daggerdidemo.data.mapper.DiscoverMoviesResponseMapper
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import javax.inject.Inject

class MovieRestDataSource
    @Inject constructor(private val apiMovie: ApiMovie) : BaseRestDataStore(), MovieDataSource {

    private val mapper: DiscoverMoviesResponseMapper by lazy { DiscoverMoviesResponseMapper() }

    override fun list(): List<Movie> {
        return mapper.transform(parseResult { apiMovie.listMovies().execute() }!!)
    }

}