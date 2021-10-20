package com.mkodo.training.stevenw.movieapp.api

import android.util.Log
import com.mkodo.training.stevenw.movieapp.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieRepositoryImpl : MovieRepository {

    val api = TheMovieDbApi.create()

    var listOfMovies: List<Movie>? = null

    override suspend fun getMovie(id: String) =
        withContext(Dispatchers.IO) {
            listOfMovies?.find { it.id == id } ?: getMovieFromApi(id)
        }

    private suspend fun getMovieFromApi(id: String): Movie {
        Log.i("MovieRepositoryImpl", "getMovieFromApi: ")
        return api.getMovie(id)
    }

    override suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        withContext(Dispatchers.IO) {
            val movieResults = api.getTrending(mediaType, timeWindow)
            listOfMovies = movieResults.results
            movieResults
        }
}
