package com.apple.itunesapi.presentation.di

import android.app.Application
import androidx.room.Room
import com.apple.itunesapi.data.db.MovieDao
import com.apple.itunesapi.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module for getting MovieDatabase and MovieDao instance
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application): MovieDatabase{
        return Room.databaseBuilder(app, MovieDatabase::class.java,"movie_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(movieDatabase: MovieDatabase): MovieDao{
        return movieDatabase.getMovieDao()
    }

}