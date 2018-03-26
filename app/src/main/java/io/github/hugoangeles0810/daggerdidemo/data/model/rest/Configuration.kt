package io.github.hugoangeles0810.daggerdidemo.data.model.rest

import com.google.gson.annotations.SerializedName
import java.util.*

data class Configuration(
        @SerializedName("images")
        val imageConfig: ImageConfig
) {
    data class ImageConfig(
            @SerializedName("base_url")
            val baseUrl: String,
            @SerializedName("backdrop_sizes")
            val backdropSizes: Array<String>,
            @SerializedName("poster_sizes")
            val posterSizes: Array<String>) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ImageConfig

            if (baseUrl != other.baseUrl) return false
            if (!Arrays.equals(backdropSizes, other.backdropSizes)) return false
            if (!Arrays.equals(posterSizes, other.posterSizes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = baseUrl.hashCode()
            result = 31 * result + Arrays.hashCode(backdropSizes)
            result = 31 * result + Arrays.hashCode(posterSizes)
            return result
        }
    }
}