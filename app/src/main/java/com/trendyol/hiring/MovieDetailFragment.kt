package com.trendyol.hiring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.trendyol.hiring.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MovieDetailFragment constructor(private val movieId: Int) : Fragment() {

    private val viewModel by viewModels<MovieDetailViewModel>()

    private var binding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater).also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.init(movieId)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                viewModel.loadingState.collect {
                    it ?: return@collect
                    onLoading()
                }
            }

            viewModel.errorState.collect {
                val exception = it ?: return@collect
                onError(exception)
            }

            launch {
                viewModel.movieDetail.collect {
                    val movieDetail = it ?: return@collect

                    with(binding!!) {
                        Glide.with(backdropImageView)
                            .load("https://image.tmdb.org/t/p/w500/" + movieDetail.backdrop_path)
                            .placeholder(R.drawable.img_movie_placeholder)
                            .into(backdropImageView)

                        titleTextView.text = movieDetail.title
                        oviewViewTextView.text = movieDetail.overview
                        // Rating should be shown after 2 seconds depends on business requirements
                        delay(DELAY_TO_SHOW_RATING)
                        ratingTextView.text = movieDetail.vote_average.toString()
                    }
                }
            }
        }
    }

    private fun onLoading() {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    private fun onError(exception: Exception) {
        Toast.makeText(requireContext(), exception.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val DELAY_TO_SHOW_RATING = 2000L
    }
}