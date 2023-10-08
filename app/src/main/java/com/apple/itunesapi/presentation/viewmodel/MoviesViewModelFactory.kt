package com.apple.itunesapi.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apple.itunesapi.domain.usecase.GetMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSavedFavoriteMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSearchedMoviesUseCase
import com.apple.itunesapi.domain.usecase.SaveFavoriteMoviesUseCase

//ViewModelFactory for MoviesViewModel

class MoviesViewModelFactory(private val app: Application,
                             private val getMoviesUseCase: GetMoviesUseCase,
                             private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
                             private val saveFavoriteMoviesUseCase: SaveFavoriteMoviesUseCase,
                             private val getSavedFavoriteMoviesUseCase: GetSavedFavoriteMoviesUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesViewModel(
            app,
            getMoviesUseCase,
            getSearchedMoviesUseCase,
            saveFavoriteMoviesUseCase,
            getSavedFavoriteMoviesUseCase
        ) as T
    }

}