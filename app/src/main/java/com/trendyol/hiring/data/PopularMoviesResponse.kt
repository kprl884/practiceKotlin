package com.trendyol.hiring.data

class PopularMoviesResponse(
    var page: Int?,
    var results: List<Movie>,
    var totalPages: Int?,
    var totalResults: Int?
)
