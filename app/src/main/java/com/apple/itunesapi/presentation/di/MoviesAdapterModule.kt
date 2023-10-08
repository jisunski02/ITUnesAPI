package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.presentation.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module for getting MovieAdapter instance
@Module
@InstallIn(SingletonComponent::class)
class MoviesAdapterModule {

    @Singleton
    @Provides
    fun providesMoviesAdapter(): MoviesAdapter{
        return MoviesAdapter()
    }
}