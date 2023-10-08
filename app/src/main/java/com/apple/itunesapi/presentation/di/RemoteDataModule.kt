package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.data.api.APIService
import com.apple.itunesapi.data.repository.dataSource.MoviesRemoteDataSource
import com.apple.itunesapi.data.repository.dataSourceImpl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Module for getting MoviesRemoteDataSource instance
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesMoviesRemoteDataSource(apiService: APIService): MoviesRemoteDataSource{
            return MoviesRemoteDataSourceImpl(apiService)
    }

}