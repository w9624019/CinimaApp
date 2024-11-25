package uk.ac.tees.mad.w9624019.cinimaapp.data.models

import com.google.gson.annotations.SerializedName
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.Video

data class VideoResponse(
    @SerializedName("results") val results: List<Video>
)