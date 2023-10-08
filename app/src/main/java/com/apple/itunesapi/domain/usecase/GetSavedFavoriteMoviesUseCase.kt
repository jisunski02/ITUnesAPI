package com.apple.itunesapi.domain.usecase

import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

//Pass values here from MovieRepository
class GetSavedFavoriteMoviesUseCase(private val movieListRepository: MoviesRepository) {

    fun execute(): Flow<List<Movies>>{
        return movieListRepository.getSavedFavoriteMovies()
    }
}