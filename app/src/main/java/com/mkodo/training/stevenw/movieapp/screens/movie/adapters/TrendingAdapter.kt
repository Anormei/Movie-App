package com.mkodo.training.stevenw.movieapp.screens.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mkodo.training.stevenw.movieapp.R
import com.mkodo.training.stevenw.movieapp.models.Movie

class TrendingAdapter(private val context: Context?, private val callback: TrendingCallback) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    var movies: List<Movie>? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton: ImageButton
        var movie: Movie? = null

        init {
            imageButton = itemView.findViewById(R.id.movie_button)
            imageButton.setOnClickListener {
                movie?.let { it1 -> callback.showMovie(it1) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trending_movie_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies?.get(position)

        Glide
            .with(context!!)
            .load(movie?.getPosterUri(0))
            .centerCrop()
            .into(holder.imageButton)

        holder.movie = movie
    }

    override fun getItemCount() = movies?.size ?: 0

}