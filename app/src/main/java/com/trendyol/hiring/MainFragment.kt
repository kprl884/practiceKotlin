package com.trendyol.hiring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.trendyol.hiring.data.PopularMoviesResponse
import com.trendyol.hiring.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    private var binding: FragmentMainBinding? = null

    private val adapter = MoviesAdapter { id ->
        (requireActivity() as? BaseActivity)
            ?.replaceFragment(MovieDetailFragment(id), addToBackStack = true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater).also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.moviesRecyclerView.adapter = adapter

        mainViewModel.init(
            onLoading = {
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
            },
            onFailure = {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                println(it.message)
            },
            onSuccess = {
                val movieItems = mapMovieItems(it)
                adapter.setMovies(movieItems)
            }
        )
    }

    private fun mapMovieItems(response: PopularMoviesResponse): List<MovieItem> {
        val movieItems = mutableListOf<MovieItem>()
        for (i in 0 until response.results.size) {
            val movieItem = MovieItem(
                response.results[i].id,
                response.results[i].title,
                response.results[i].overview,
                response.results[i].poster_path ?: "",
                response.results[i].vote_average.toString()
            )
            movieItems.add(movieItem)
        }
        return movieItems
    }
}