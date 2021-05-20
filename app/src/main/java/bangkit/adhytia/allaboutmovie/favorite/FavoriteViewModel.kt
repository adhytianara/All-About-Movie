package bangkit.adhytia.allaboutmovie.favorite

import androidx.lifecycle.ViewModel
import bangkit.adhytia.allaboutmovie.core.data.MovieRepository

class FavoriteViewModel(movieRepository: MovieRepository) : ViewModel() {

    val favoriteMovie = movieRepository.getFavoriteMovie()

}

