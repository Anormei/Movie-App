package com.mkodo.training.stevenw.movieapp.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mkodo.training.stevenw.movieapp.models.Movie
import com.mkodo.training.stevenw.movieapp.models.MovieResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val api: TheMovieDbApi) : MovieRepository {


    override suspend fun getMovie(data: MutableLiveData<Movie>, id: String) {

        api.getMovie(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                if (response.body() != null) {
                    data.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("MovieRepository", "Movie failed to load: $t")
            }

        })

    }

    override suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        withContext(Dispatchers.IO) { api.getTrending(mediaType, timeWindow) }

}
