package com.mkodo.training.stevenw.movieapp.screens.movie.adapters

import com.mkodo.training.stevenw.movieapp.models.Movie

interface TrendingCallback {

    fun showMovie(movie: Movie)
}