package bangkit.adhytia.allaboutmovie.core.data.source.remote.response

data class MovieResponse(
    val id: String,
    val title: String,
    val overview: String,
    val posterURL: String,
    val backdropURL: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String,
    val popularity: String
)

