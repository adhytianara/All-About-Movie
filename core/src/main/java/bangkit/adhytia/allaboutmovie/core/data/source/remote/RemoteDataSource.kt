package bangkit.adhytia.allaboutmovie.core.data.source.remote

import android.util.Log
import bangkit.adhytia.allaboutmovie.core.data.source.remote.network.ApiResponse
import bangkit.adhytia.allaboutmovie.core.data.source.remote.network.ApiService
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovieList()
                val dataArray = response.movies
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.movies))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

