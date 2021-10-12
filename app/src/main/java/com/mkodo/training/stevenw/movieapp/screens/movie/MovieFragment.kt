package com.mkodo.training.stevenw.movieapp.screens.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mkodo.training.stevenw.movieapp.api.TheMovieDbApi
import com.mkodo.training.stevenw.movieapp.databinding.FragmentMovieBinding
import com.mkodo.training.stevenw.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment() : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Bundle().getString("id")?.let { viewModel.loadMovie(it) }
        arguments?.getString("id")?.let { viewModel.loadMovie(it) }

        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        viewModel.movie.observe(viewLifecycleOwner, {
            binding.movieTitle.text = it.title
            binding.movieDescriptionTextView.text = it.overview

            Glide
                .with(this)
                .load(it.getPosterUri(0))
                .centerCrop()
                .into(binding.movieImage)
        })



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(id: String) =
            MovieFragment().apply {
                arguments = Bundle().apply{
                    putString("id", id)
                }
            }
    }
}