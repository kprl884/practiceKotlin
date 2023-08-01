package com.trendyol.hiring.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    /**
     * @see <a href="https://developer.themoviedb.org/reference/movie-details">API Docs</href>
     */
    @GET("3/movie/{movie_id}")
    fun fetchMovieDetail(@Path("movie_id") movieId: Int): Call<MovieDetailResponse>
}
