package uk.ac.tees.mad.w9624019.cinimaapp.models

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)

