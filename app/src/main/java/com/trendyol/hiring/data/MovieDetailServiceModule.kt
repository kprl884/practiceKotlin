package com.trendyol.hiring.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit.Builder
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class MovieDetailServiceModule {

    @Provides
    fun provideMovieDetailService(retrofitBuilder: Builder): MovieDetailService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .build()
            .create()
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"
    }
}
