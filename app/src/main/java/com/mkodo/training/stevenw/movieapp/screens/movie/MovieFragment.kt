package com.mkodo.training.stevenw.movieapp.screens.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mkodo.training.stevenw.movieapp.api.TheMovieDbApi
import com.mkodo.training.stevenw.movieapp.databinding.FragmentMovieBinding
import com.mkodo.training.stevenw.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment(private val movie: Movie) : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel = MovieViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding.movieTitle.text = movie.title
        binding.movieDescriptionTextView.text = movie.overview

        Glide
            .with(this)
            .load(movie.getPosterUri(0))
            .centerCrop()
            .into(binding.movieImage)

        Log.i("MovieFragment", "Img URL = ${movie.getPosterUri(0)}")

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}