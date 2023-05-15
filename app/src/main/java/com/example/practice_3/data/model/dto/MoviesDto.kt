package com.example.practice_3.data.model.dto


import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
)