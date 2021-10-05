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


    override suspend fun getMovie(id: String) =
        withContext(Dispatchers.IO) { api.getMovie(id) }

    override suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        withContext(Dispatchers.IO) { api.getTrending(mediaType, timeWindow) }

}
