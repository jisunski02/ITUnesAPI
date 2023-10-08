package com.apple.itunesapi.data.model


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val movies: List<Movies>
)