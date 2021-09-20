package com.mkodo.training.stevenw.movieapp.screens.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.databinding.FragmentMovieBinding
import com.mkodo.training.stevenw.movieapp.databinding.FragmentMovieNavigationBinding
import com.mkodo.training.stevenw.movieapp.models.Movie
import com.mkodo.training.stevenw.movieapp.screens.movie.adapters.TrendingAdapter
import com.mkodo.training.stevenw.movieapp.screens.movie.adapters.TrendingCallback

class MovieNavigationFragment : Fragment(), TrendingCallback {

    private var _binding: FragmentMovieNavigationBinding? = null
    private val binding get() = _binding!!

    private lateinit var trendingAdapter: TrendingAdapter

    private val viewModel = MovieViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val arr = Array<String?>(30) { null }

        for (i in arr.indices) {
            arr[i] = "Button$i"
        }

        _binding = FragmentMovieNavigationBinding.inflate(inflater, container, false)
        trendingAdapter = TrendingAdapter(context, this)
        binding.trendingRecyclerView.layoutManager = GridLayoutManager(container?.context, 2)
        binding.trendingRecyclerView.adapter = trendingAdapter

        viewModel.movies.observe(viewLifecycleOwner, {
            trendingAdapter.movies = it
            trendingAdapter.notifyDataSetChanged()
        })

        viewModel.loadTrendingMovies("movie", "week")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMovie(movie: Movie) {
        activity?.apply {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.movie_master, MovieFragment(movie))
                .addToBackStack(null)
                .commit();
        }
    }

}