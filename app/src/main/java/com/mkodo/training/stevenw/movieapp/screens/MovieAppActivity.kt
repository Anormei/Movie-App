package com.mkodo.training.stevenw.movieapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.databinding.ActivityMovieBinding
import com.mkodo.training.stevenw.movieapp.screens.movie.MovieNavigationFragment


class MovieAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.movie_master, MovieNavigationFragment())
                .commit()
        }

    }

}