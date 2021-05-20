package bangkit.adhytia.allaboutmovie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}

