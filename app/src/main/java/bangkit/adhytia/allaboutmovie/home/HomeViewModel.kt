package bangkit.adhytia.allaboutmovie.home

import androidx.lifecycle.ViewModel
import bangkit.adhytia.allaboutmovie.core.data.MovieRepository

class HomeViewModel(movieRepository: MovieRepository) : ViewModel() {

    val movie = movieRepository.getAllMovie()

}

