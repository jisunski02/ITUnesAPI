package com.apple.itunesapi.presentation.di

import android.app.Application
import com.apple.itunesapi.domain.usecase.GetMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSavedFavoriteMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSearchedMoviesUseCase
import com.apple.itunesapi.domain.usecase.SaveFavoriteMoviesUseCase
import com.apple.itunesapi.presentation.viewmodel.MoviesViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Module for getting ViewModelFactory instance
@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesViewModelFactory(app: Application,
                                 getMoviesUseCase: GetMoviesUseCase,
                                 getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
                                 saveFavoriteMoviesUseCase: SaveFavoriteMoviesUseCase,
                                 getSavedFavoriteMoviesUseCase: GetSavedFavoriteMoviesUseCase): MoviesViewModelFactory{
        return MoviesViewModelFactory(app, getMoviesUseCase, getSearchedMoviesUseCase, saveFavoriteMoviesUseCase, getSavedFavoriteMoviesUseCase)
    }
}