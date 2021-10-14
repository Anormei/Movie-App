package com.mkodo.training.stevenw.movieapp.screens.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.api.MovieRepositoryImpl
import com.mkodo.training.stevenw.movieapp.api.TheMovieDbApi
import com.mkodo.training.stevenw.movieapp.models.Movie
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository = MovieRepositoryImpl
) : ViewModel() {

    val movie = MutableLiveData<Movie>()

    fun loadMovie(id: String) {
        viewModelScope.launch {
            val result = movieRepository.getMovie(id)
            movie.value = result
        }
    }
}