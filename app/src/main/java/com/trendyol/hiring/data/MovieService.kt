package com.trendyol.hiring.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    /**
     * @see <a href="https://developers.themoviedb.org/3/movies/get-popular-movies">API Docs</href>
     */
    @GET("/3/movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): PopularMoviesResponse
}
