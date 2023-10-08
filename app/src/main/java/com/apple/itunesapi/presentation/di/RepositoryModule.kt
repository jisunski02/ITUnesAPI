package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.data.repository.dataSource.MoviesLocalDataSource
import com.apple.itunesapi.data.repository.dataSource.MoviesRemoteDataSource
import com.apple.itunesapi.domain.repository.MoviesRepository
import com.example.itunessearchapi.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module for MovieRepository instance
@Module
@InstallIn(SingletonComponent::class) //Automatic built set of components
class RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(moviesRemoteDataSource: MoviesRemoteDataSource,
                                moviesLocalDataSource: MoviesLocalDataSource): MoviesRepository{
        return MoviesRepositoryImpl(moviesRemoteDataSource, moviesLocalDataSource)
    }

}