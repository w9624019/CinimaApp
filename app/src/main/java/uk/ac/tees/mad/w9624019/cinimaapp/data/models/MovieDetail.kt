package uk.ac.tees.mad.w9624019.cinimaapp.data.models

import com.google.gson.annotations.SerializedName
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.Genre
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.VideoResponse

data class MovieDetail(
    @SerializedName("id") val id: Long,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterUrl: String,
    @SerializedName("overview") val synopsis: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("videos") val videos: VideoResponse,
)