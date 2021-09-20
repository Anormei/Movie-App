package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.api.MovieRepositoryImpl
import com.mkodo.training.stevenw.movieapp.api.TheMovieDbApi
import com.mkodo.training.stevenw.movieapp.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel(val movieRepository: MovieRepository = MovieRepositoryImpl(TheMovieDbApi.create())) : ViewModel() {

    //TODO Hook-up showError in activity
    val showError = MutableLiveData<String>()
    val movies = MutableLiveData<List<Movie>>()

    fun loadTrendingMovies(mediaType: String, timeWindow: String) {
        viewModelScope.launch{
            try {
                val results = movieRepository.getTrendingMovies("movie", "week")
                movies.value = results.results
            } catch(e: Throwable) {
                showError.value = "Something went wrong"
            }
        }
    }
}