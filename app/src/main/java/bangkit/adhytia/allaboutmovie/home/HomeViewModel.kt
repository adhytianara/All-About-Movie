package bangkit.adhytia.allaboutmovie.home

import androidx.lifecycle.ViewModel
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie()
}

