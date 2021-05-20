package bangkit.adhytia.allaboutmovie.detail

import androidx.lifecycle.ViewModel
import bangkit.adhytia.allaboutmovie.core.data.MovieRepository
import bangkit.adhytia.allaboutmovie.core.data.source.local.entity.MovieEntity

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun setFavoriteMovie(movie: MovieEntity, newStatus: Boolean) =
        movieRepository.setFavoriteMovie(movie, newStatus)
}

