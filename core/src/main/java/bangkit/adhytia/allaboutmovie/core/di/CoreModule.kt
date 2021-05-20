package bangkit.adhytia.allaboutmovie.core.di

import androidx.room.Room
import bangkit.adhytia.allaboutmovie.core.data.MovieRepository
import bangkit.adhytia.allaboutmovie.core.data.source.local.LocalDataSource
import bangkit.adhytia.allaboutmovie.core.data.source.local.room.MovieDatabase
import bangkit.adhytia.allaboutmovie.core.data.source.remote.RemoteDataSource
import bangkit.adhytia.allaboutmovie.core.data.source.remote.network.ApiService
import bangkit.adhytia.allaboutmovie.core.domain.repository.IMovieRepository
import bangkit.adhytia.allaboutmovie.core.utils.AppExecutors
import bangkit.adhytia.allaboutmovie.core.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}