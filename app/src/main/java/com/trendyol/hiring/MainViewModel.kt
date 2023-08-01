package com.trendyol.hiring

import androidx.lifecycle.ViewModel
import com.trendyol.hiring.data.MovieService
import com.trendyol.hiring.data.PopularMoviesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create

@OptIn(DelicateCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val retrofitBuilder: Retrofit.Builder
) : ViewModel() {

    fun init(
        page: Int = 1,
        onLoading: () -> Unit = {},
        onFailure: (Exception) -> Unit = {},
        onSuccess: (PopularMoviesResponse) -> Unit = {}
    ) = GlobalScope.launch(Dispatchers.Main) {
        try {
            onLoading()
            val popularMovies = getMovieService().fetchPopularMovies(page)
            onSuccess(popularMovies)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    private fun getMovieService(): MovieService {
        return retrofitBuilder
            .baseUrl("https://api.themoviedb.org/")
            .build()
            .create()
    }
}
