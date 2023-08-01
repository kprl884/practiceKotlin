package com.trendyol.hiring

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.trendyol.hiring.databinding.ItemMovieBinding

@SuppressLint("NotifyDataSetChanged")
class MoviesAdapter(
    private val onMovieClicked: (Int) -> Unit
) : Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies = listOf<MovieItem>()

    fun setMovies(movies: List<MovieItem>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClicked)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onTvShowClicked: (Int) -> Unit
    ) : ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            val movie = movies[position]

            Glide.with(logoImageView)
                .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
                .placeholder(R.drawable.img_movie_placeholder)
                .into(logoImageView)

            titleTextView.text = movie.title
            overviewTextView.text = movie.overview
            ratingTextView.text = movie.voteAverage
            val bottomMargin = if (position == movies.size - 1) dpToPx(8) else 0
            root.updateLayoutParams<RecyclerView.LayoutParams> {
                this.bottomMargin = bottomMargin
            }
            root.setOnClickListener {
                onTvShowClicked(movie.id)
            }
        }
    }

    private fun dpToPx(dp: Int): Int = (dp.toDouble() * Resources.getSystem().displayMetrics.density).toInt()
}
