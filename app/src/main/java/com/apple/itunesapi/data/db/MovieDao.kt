package com.apple.itunesapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apple.itunesapi.data.model.Movies
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movies: Movies)

    @Query("SELECT * FROM movies")
    fun getSavedFavoriteMovies(): Flow<List<Movies>>

}