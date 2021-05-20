package bangkit.adhytia.allaboutmovie.favorite

import androidx.lifecycle.ViewModel
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie()
}

