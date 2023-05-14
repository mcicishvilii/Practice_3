package com.example.practice_3.domain.repository

import com.example.practice_3.domain.model.MoviesDomain

interface MoviesRepository {
    suspend fun refreshMovies(): List<MoviesDomain>
    suspend fun removeAll()

}