package com.example.practice_3.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice_3.data.model.room.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 7)
abstract class DataBase:RoomDatabase() {
    abstract val moviesDao: MoviesDao
}