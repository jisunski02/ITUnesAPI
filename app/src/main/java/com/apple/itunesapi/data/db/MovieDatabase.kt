package com.apple.itunesapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apple.itunesapi.data.model.Movies

@Database(
    entities = [Movies::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}