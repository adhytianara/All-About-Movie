package bangkit.adhytia.allaboutmovie.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val overview: String,
    val posterURL: String,
    val backdropURL: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String,
    val popularity: String,
    val isFavorite: Boolean
) : Parcelable