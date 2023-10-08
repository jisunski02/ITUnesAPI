package com.apple.itunesapi.data.repository.dataSource

import com.apple.itunesapi.data.model.MoviesResponse
import retrofit2.Response

//Interface that communicates with the APIService

interface MoviesRemoteDataSource {

    suspend fun getMovies(term: String, media:String, limit: String): Response<MoviesResponse>

    suspend fun getSearchedMovies(term: String, media:String, limit: String): Response<MoviesResponse>
}