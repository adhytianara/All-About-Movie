package bangkit.adhytia.allaboutmovie.core.utils

import bangkit.adhytia.allaboutmovie.core.data.source.local.entity.MovieEntity
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.MovieResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterURL = it.posterURL,
                backdropURL = it.backdropURL,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
}