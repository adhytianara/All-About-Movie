package bangkit.adhytia.allaboutmovie.core.domain.usecase

import bangkit.adhytia.allaboutmovie.core.domain.model.Movie
import bangkit.adhytia.allaboutmovie.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}