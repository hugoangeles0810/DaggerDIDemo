package io.github.hugoangeles0810.daggerdidemo.data.repositories

import io.github.hugoangeles0810.daggerdidemo.data.datasource.ConfigurationDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.MovieDataSource
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie
import javax.inject.Inject


class MovieRepository
    @Inject constructor(private val movieDataSource: MovieDataSource,
                      private val configurationDataSource: ConfigurationDataSource) {

    fun listMovies(): List<Movie> {
        val movies = movieDataSource.list()
        val configuration = configurationDataSource.get()

        return movies.map {
            it.copy(posterUrl = configuration.getPosterUrl(it.posterUrl ?: ""),
                    backdropUrl = configuration.getBackdropUrl(it.backdropUrl ?: ""))
        }
    }
}