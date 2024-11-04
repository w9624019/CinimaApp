package uk.ac.tees.mad.w9624019.cinimaapp.data

data class Planet(
    val climate: String,
    val created: String,
    val diameter: Int,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val oribitalPeriod: Int,
    val residents: List<String>,
    val rotation_period: Int,
    val surface_water: String,
    val terrain: String,
    val url: String
)

data class StarWarsData(
    val count: Int,
    val next: String,
    val prev: String?,
    val results: List<Planet>
)