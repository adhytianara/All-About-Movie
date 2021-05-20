package bangkit.adhytia.allaboutmovie.core.di

import android.content.Context
import bangkit.adhytia.allaboutmovie.core.data.MovieRepository
import bangkit.adhytia.allaboutmovie.core.data.source.local.LocalDataSource
import bangkit.adhytia.allaboutmovie.core.data.source.local.room.MovieDatabase
import bangkit.adhytia.allaboutmovie.core.data.source.remote.RemoteDataSource
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieInteractor
import bangkit.adhytia.allaboutmovie.core.domain.usecase.MovieUseCase
import bangkit.adhytia.allaboutmovie.core.utils.AppExecutors
import bangkit.adhytia.allaboutmovie.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}
