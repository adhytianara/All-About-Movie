package bangkit.adhytia.allaboutmovie.core.utils

import android.content.Context
import bangkit.adhytia.allaboutmovie.R
import bangkit.adhytia.allaboutmovie.core.data.source.remote.response.MovieResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.movie).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("results")
        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val image = course.getString("poster_path")
            val backdrop = course.getString("backdrop_path")

            val courseResponse = MovieResponse(
                id = course.getString("id"),
                title = course.getString("original_title"),
                overview = course.getString("overview"),
                posterPath = "https://image.tmdb.org/t/p/w500$image",
                backdropPath = "https://image.tmdb.org/t/p/w500$backdrop",
                releaseDate = course.getString("release_date"),
                voteAverage = course.getString("vote_average"),
                voteCount = course.getString("vote_count"),
                popularity = course.getString("popularity"),
            )
            list.add(courseResponse)
        }
        return list
    }
}

