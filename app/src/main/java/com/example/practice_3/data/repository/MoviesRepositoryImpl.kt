package com.example.practice_3.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.example.practice_3.data.model.dto.toDomain
import com.example.practice_3.data.model.room.toDomain
import com.example.practice_3.data.service.MoviesService
import com.example.practice_3.domain.model.MoviesDomain
import com.example.practice_3.domain.model.toRoom
import com.example.practice_3.domain.repository.MoviesRepository
import com.example.practice_3.data.db.MoviesDao
import javax.inject.Inject

const val TAG = "MCICISHVILII"

class MoviesRepositoryImpl @Inject constructor(
    private val service: MoviesService,
    private val dao: MoviesDao,
    private val context: Context,
) : MoviesRepository {
    override suspend fun refreshMovies(): List<MoviesDomain> {
        if (isInternetConnected(context)) {
            val response = service.getPopularMovies("acdbc7ef61877f0d6b3e29d062218ccc")
            if (response.isSuccessful) {
                val moviesListFromApi = response.body()?.results?.mapNotNull { it?.toDomain() }
                if (!moviesListFromApi.isNullOrEmpty()) {
                    dao.insert(moviesListFromApi.map { it.toRoom() })
                }
            }
        }
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun removeAll() {
        dao.deleteAll()
    }

    private fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
