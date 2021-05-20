package bangkit.adhytia.allaboutmovie.core.data

import androidx.lifecycle.LiveData
import bangkit.adhytia.allaboutmovie.core.data.source.local.LocalDataSource
import bangkit.adhytia.allaboutmovie.core.data.source.local.entity.MovieEntity
import bangkit.adhytia.allaboutmovie.core.data.source.remote.RemoteDataSource
import bangkit.adhytia.allaboutmovie.core.data.source.remote.network.ApiResponse
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.MovieResponse
import bangkit.adhytia.allaboutmovie.core.utils.AppExecutors
import bangkit.adhytia.allaboutmovie.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    fun getAllMovie(): LiveData<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMovie()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> {
        return localDataSource.getFavoriteMovie()
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    }
}

