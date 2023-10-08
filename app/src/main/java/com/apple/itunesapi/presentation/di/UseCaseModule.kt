package com.apple.itunesapi.presentation.di

import com.apple.itunesapi.domain.repository.MoviesRepository
import com.apple.itunesapi.domain.usecase.GetMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSavedFavoriteMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSearchedMoviesUseCase
import com.apple.itunesapi.domain.usecase.SaveFavoriteMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetMoviesUseCase(movieRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun providesGetSearchedMoviesUseCase(movieRepository: MoviesRepository): GetSearchedMoviesUseCase {
        return GetSearchedMoviesUseCase(movieRepository)
    }


    @Singleton
    @Provides
    fun providesSaveFavoriteMovieUseCase(movieRepository: MoviesRepository): SaveFavoriteMoviesUseCase {
        return SaveFavoriteMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavdeFavoriteMovieUseCase(movieRepository: MoviesRepository): GetSavedFavoriteMoviesUseCase {
        return GetSavedFavoriteMoviesUseCase(movieRepository)
    }
}