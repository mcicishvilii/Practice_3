package com.example.practice_3.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practice_3.domain.model.MoviesDomain

@Entity(tableName = "movies")
data class MoviesEntity(
    val adult: Boolean?,
    @PrimaryKey(autoGenerate = false)
    val id:Int?,
    val backdropPath: String?,
    @ColumnInfo(name = "movie_title")
    val originalTitle: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val overview:String?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun MoviesEntity.toDomain(): MoviesDomain {
    return MoviesDomain(
       adult,id, backdropPath,originalTitle, posterPath, releaseDate, title, overview, voteAverage, voteCount
    )
}