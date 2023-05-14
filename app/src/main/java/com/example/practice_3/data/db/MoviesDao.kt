package com.example.practice_3.data.db

import androidx.room.*
import com.example.practice_3.data.model.room.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MoviesEntity>

    @Query("SELECT * FROM movies WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies WHERE movie_title LIKE :first")
    fun findByName(first: String): List<MoviesEntity>

    @Insert
    fun insertAll(vararg users: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(moviesList: List<MoviesEntity>)

    @Delete
    fun delete(movie: MoviesEntity)

    @Query("DELETE FROM movies")
    fun deleteAll()

}