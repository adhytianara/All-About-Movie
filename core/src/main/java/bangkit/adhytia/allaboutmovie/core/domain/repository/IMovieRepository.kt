package bangkit.adhytia.allaboutmovie.core.domain.repository

import androidx.lifecycle.LiveData
import bangkit.adhytia.allaboutmovie.core.data.Resource
import bangkit.adhytia.allaboutmovie.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}
