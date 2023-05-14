package com.example.practice_3.domain.model

import android.os.Parcelable
import com.example.practice_3.data.model.room.MoviesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomain(
    val adult: Boolean?,
    val id: Int?,
    val backdropPath: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val overview:String?,
    val voteAverage: Double?,
    val voteCount: Int?
):Parcelable

fun MoviesDomain.toRoom(): MoviesEntity {
    return MoviesEntity(
        adult,id, backdropPath, originalTitle, posterPath, releaseDate, title, overview,voteAverage, voteCount
    )
}
