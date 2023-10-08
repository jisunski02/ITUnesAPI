package com.apple.itunesapi.data.repository.dataSourceImpl

import com.apple.itunesapi.data.api.APIService
import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.repository.dataSource.MoviesRemoteDataSource
import retrofit2.Response

//implement MoviesRemoteDataSource here
//Add parameters from APIService (getMovies) function to the constructor, and also add instance of APIService to the parameter

class MoviesRemoteDataSourceImpl(
    private val apiService: APIService,
): MoviesRemoteDataSource {

    override suspend fun getMovies(term: String, media:String, limit: String): Response<MoviesResponse> {
        return apiService.getMovies(term, media, limit)
    }

    override suspend fun getSearchedMovies(
        term: String,
        media: String,
        limit: String
    ): Response<MoviesResponse> {
        return apiService.getSearchedMovies(term, media, limit)
    }
}