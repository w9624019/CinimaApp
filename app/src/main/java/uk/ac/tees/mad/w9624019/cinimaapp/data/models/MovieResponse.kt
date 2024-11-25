package uk.ac.tees.mad.w9624019.cinimaapp.data.models

import com.google.gson.annotations.SerializedName
import uk.ac.tees.mad.w9624019.cinimaapp.data.models.Movie

data class MovieResponse(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,   // Total number of results
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("page") val page: Int,

    )
