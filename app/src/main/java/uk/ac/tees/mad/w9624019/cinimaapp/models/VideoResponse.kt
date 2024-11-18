package uk.ac.tees.mad.w9624019.cinimaapp.models

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("results") val results: List<Video>
)