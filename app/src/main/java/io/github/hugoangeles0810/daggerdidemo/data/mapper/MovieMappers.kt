package io.github.hugoangeles0810.daggerdidemo.data.mapper

import io.github.hugoangeles0810.daggerdidemo.data.model.rest.DiscoverMoviesResponse
import io.github.hugoangeles0810.daggerdidemo.data.model.rest.MovieItemResponse
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Movie


class DiscoverMoviesResponseMapper {

    fun transform(discoverMoviesResponse: DiscoverMoviesResponse): List<Movie> {
        val movieItemResponseMapper = MovieItemResponseMapper()
        return discoverMoviesResponse.results.map { movieItemResponseMapper.transform(it) }
    }
}

class MovieItemResponseMapper {

    fun transform(itemResponse: MovieItemResponse): Movie {
        return Movie(
                itemResponse.id,
                itemResponse.title,
                itemResponse.overview,
                itemResponse.popularity,
                itemResponse.posterPath,
                itemResponse.backdropPath
        )
    }
}