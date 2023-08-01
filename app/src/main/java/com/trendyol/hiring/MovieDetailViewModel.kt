package com.trendyol.hiring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trendyol.hiring.data.MovieDetailResponse
import com.trendyol.hiring.data.MovieDetailService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailService: MovieDetailService
) : ViewModel() {

    private val _loadingState = MutableStateFlow<Unit?>(null)
    val loadingState: StateFlow<Unit?> = _loadingState.asStateFlow()

    private val _errorState = MutableStateFlow<Exception?>(null)
    val errorState: StateFlow<Exception?> = _errorState.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetailResponse?>(null)
    val movieDetail: StateFlow<MovieDetailResponse?> = _movieDetail.asStateFlow()

    fun init(movieId: Int) = viewModelScope.launch {
        _loadingState.value = Unit
        try {
            val movieDetail = movieDetailService.fetchMovieDetail(movieId).execute().body()
            _movieDetail.value = movieDetail
        } catch (exception: Exception) {
            _errorState.value = exception
        }
    }
}
