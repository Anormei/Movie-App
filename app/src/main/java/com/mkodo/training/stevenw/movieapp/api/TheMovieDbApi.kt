package com.mkodo.training.stevenw.movieapp.api

import com.mkodo.training.stevenw.movieapp.models.Movie
import com.mkodo.training.stevenw.movieapp.models.MovieResults
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApi {

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: String): Call<Movie>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): MovieResults

    companion object {
        private const val API_KEY: String = "0a65a3a761c4ac768bb0d36458e32c20"
        private const val ENGLISH_US: String = "en-US"

        fun create(): TheMovieDbApi {

            val movieClient = OkHttpClient.Builder()
                .addInterceptor(MovieInterceptor(API_KEY, ENGLISH_US))
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(movieClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TheMovieDbApi::class.java)
        }

    }
}