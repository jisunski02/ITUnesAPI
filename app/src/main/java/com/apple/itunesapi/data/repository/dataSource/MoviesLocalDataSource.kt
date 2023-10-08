package com.apple.itunesapi.data.repository.dataSource

import com.apple.itunesapi.data.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun saveFavoriteMoviesToDB(movies: Movies)

    fun getSavedFavoriteMovies(): Flow<List<Movies>>
}