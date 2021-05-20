package bangkit.adhytia.allaboutmovie.core.utils

import bangkit.adhytia.allaboutmovie.core.data.source.local.entity.MovieEntity
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.MovieResponse
import bangkit.adhytia.allaboutmovie.core.domain.model.Movie

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

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterURL = it.posterURL,
                backdropURL = it.backdropURL,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                popularity = it.popularity,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        posterURL = input.posterURL,
        backdropURL = input.backdropURL,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        popularity = input.popularity,
        isFavorite = input.isFavorite
    )
}