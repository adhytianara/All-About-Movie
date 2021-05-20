package bangkit.adhytia.allaboutmovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("page")
    val page: String,

    @field:SerializedName("total_pages")
    val totalPages: String,

    @field:SerializedName("total_results")
    val totalResults: String,

    @field:SerializedName("results")
    val movies: List<MovieResponse>
)