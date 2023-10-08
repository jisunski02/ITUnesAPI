package com.apple.itunesapi.data.repository.dataSourceImpl

import com.apple.itunesapi.data.db.MovieDao
import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.data.repository.dataSource.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(
    private val movieDao: MovieDao
): MoviesLocalDataSource {
    override suspend fun saveFavoriteMoviesToDB(movies: Movies) {
        return movieDao.insertFavoriteMovie(movies)
        }

    override fun getSavedFavoriteMovies(): Flow<List<Movies>> {
        return movieDao.getSavedFavoriteMovies()
    }
}