package bangkit.adhytia.allaboutmovie.core.domain.usecase

import androidx.lifecycle.LiveData
import bangkit.adhytia.allaboutmovie.core.data.Resource
import bangkit.adhytia.allaboutmovie.core.domain.model.Movie

interface MovieUseCase {

    fun getAllMovie(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}