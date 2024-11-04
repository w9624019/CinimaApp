package uk.ac.tees.mad.w9624019.cinimaapp.api

import android.util.Log
import uk.ac.tees.mad.w9624019.cinimaapp.data.StarWarsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://swapi.dev/api/"

interface ApiService {
    @GET("planets/")
    fun getPlanetList(): Call<StarWarsData>
}

class ApiCall() {
    suspend fun getPlanets(
        onSuccess: (StarWarsData) -> Unit = { _ -> },
        onError: (message: String) ->  Unit = {}
    ) {
        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // <-- To handle simple types like String
                .build()

        // Create an concrete ApiService instance from the Retrofit instance.
        val service: ApiService = retrofit.create(ApiService::class.java)

        // Prepare the request
        val call: Call<StarWarsData> = service.getPlanetList()

        // Add the request to the queue
        call.enqueue(object : Callback<StarWarsData> {
            override fun onResponse(call: Call<StarWarsData>?, response: Response<StarWarsData>?) {
                response?.let {
                    if(response.isSuccessful) {
                        Log.d("package:mine", response.raw().message())
                        onSuccess(response.body())
                    }
                    else {
                        Log.e("package:mine", "Something went wrong - ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<StarWarsData>?, t: Throwable?) {
                Log.e("package:mine", "Failed to fetch data went wrong - ${t?.message ?: "----"}")
                onError("Could not fetch data")
            }

        })
    }
}