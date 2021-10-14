package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.api.MovieRepositoryImpl
import com.mkodo.training.stevenw.movieapp.api.TheMovieDbApi
import com.mkodo.training.stevenw.movieapp.models.Movie
import kotlinx.coroutines.launch

class MovieNavigationViewModel(
    private val movieRepository: MovieRepository = MovieRepositoryImpl
): ViewModel() {

    val showError = MutableLiveData<Int>()
    val movies = MutableLiveData<List<Movie>>()

    fun loadTrendingMovies(mediaType: String, timeWindow: String) {
        viewModelScope.launch {
            try {
                val results = movieRepository.getTrendingMovies(mediaType, timeWindow)
                movies.value = results.results
            } catch (e: Throwable) {
                showError.value = R.string.error_loading_movies
            }
        }
    }
}