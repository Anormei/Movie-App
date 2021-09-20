package com.mkodo.training.stevenw.movieapp.screens

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.databinding.ActivityMovieBinding
import com.mkodo.training.stevenw.movieapp.screens.movie.MovieNavigationFragment

class MovieAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.movie_master, MovieNavigationFragment())
            .commit()

    }

}