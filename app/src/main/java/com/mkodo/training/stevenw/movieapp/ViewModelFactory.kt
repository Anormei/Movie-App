package com.mkodo.training.stevenw.movieapp

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.mkodo.training.stevenw.movieapp.api.MovieRepository
import com.mkodo.training.stevenw.movieapp.screens.movie.MovieNavigationViewModel
import com.mkodo.training.stevenw.movieapp.screens.movie.MovieViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val movieRepository: MovieRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when{
            isAssignableFrom(MovieNavigationViewModel::class.java) ->
                MovieNavigationViewModel(movieRepository)
            isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(movieRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}