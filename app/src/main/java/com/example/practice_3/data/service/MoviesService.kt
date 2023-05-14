package com.example.practice_3.data.service

import com.example.practice_3.data.model.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String,
    ): Response<MoviesDto>

}