package com.apple.itunesapi.data.api

import com.apple.itunesapi.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search")
    suspend fun getMovies(
        @Query("term")
        term: String,
        @Query("media")
        media: String,
        @Query("limit")
        limit: String
    ): Response<MoviesResponse>

    @GET("search")
    suspend fun getSearchedMovies(
        @Query("term")
        term: String,
        @Query("media")
        media: String,
        @Query("limit")
        limit: String
    ): Response<MoviesResponse>


}