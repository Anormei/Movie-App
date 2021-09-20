package com.mkodo.training.stevenw.movieapp.api

import androidx.lifecycle.MutableLiveData
import com.mkodo.training.stevenw.movieapp.models.Movie
import com.mkodo.training.stevenw.movieapp.models.MovieResults

interface MovieRepository {

    suspend fun getMovie(data: MutableLiveData<Movie>, id: String)
    suspend fun getTrendingMovies(
        mediaType: String,
        timeWindow: String
    ): MovieResults

}