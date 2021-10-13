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

//TODO make it a singleton
object MovieRepositoryImpl : MovieRepository {

    val api = TheMovieDbApi.create()

    //TODO local list of movies

    val map = mutableMapOf<String, Movie>()

    override suspend fun getMovie(id: String) =
        withContext(Dispatchers.IO) {
            if (map[id] == null) map[id] = api.getMovie(id)
            map[id]!!
        }

    override suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        withContext(Dispatchers.IO) {
            val movieResults = api.getTrending(mediaType, timeWindow)
            for(movie: Movie in movieResults.results){
                if(map[movie.id] == null) map[movie.id] = movie
            }
            movieResults
        }

    private suspend fun isMovieLoaded(id: String){

    }

    private suspend fun loadMovie(id: String){

    }
}
