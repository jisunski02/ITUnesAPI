package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.data.db.MovieDao
import com.apple.itunesapi.data.repository.dataSource.MoviesLocalDataSource
import com.apple.itunesapi.data.repository.dataSourceImpl.MoviesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module for getting MovieLocalDataSource instance
@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(movieDao: MovieDao): MoviesLocalDataSource{
        return MoviesLocalDataSourceImpl(movieDao)
    }
}