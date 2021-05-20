package bangkit.adhytia.allaboutmovie.core.data.source.remote.network

import bangkit.adhytia.allaboutmovie.BuildConfig
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=${BuildConfig.TMDB_API_KEY}")
    fun getPopularMovieList(): Call<ListMovieResponse>
}
