package com.apple.itunesapi.domain.repository

import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(term: String, media:String, limit: String): Resource<MoviesResponse>
    suspend fun getSearchedMovies(term: String, media:String, limit: String): Resource<MoviesResponse>
    suspend fun saveFavoriteMovies(movies: Movies)
    fun getSavedFavoriteMovies(): Flow<List<Movies>>

}