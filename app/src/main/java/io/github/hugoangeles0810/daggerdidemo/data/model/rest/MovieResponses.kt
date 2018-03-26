package io.github.hugoangeles0810.daggerdidemo.data.model.rest

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
        @SerializedName("page")
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("results")
        val results: List<MovieItemResponse> = emptyList()
)

data class MovieItemResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String? = null,
        @SerializedName("backdrop_path")
        val backdropPath: String? = null
)