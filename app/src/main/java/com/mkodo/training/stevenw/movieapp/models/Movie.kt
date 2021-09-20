package com.mkodo.training.stevenw.movieapp.models

import android.net.Uri

// TODO Look into serialised annotation rename poster_path to posterPath
data class Movie(var title: String, var overview: String, var poster_path: String){

    @Transient
    private val imageURL = "https://image.tmdb.org/t/p"

    fun getPosterUri(size: Int): Uri{
        return Uri.parse("https://image.tmdb.org/t/p/${if(size > 0) "w$size" else "original"}$poster_path")

    }
}
