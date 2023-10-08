package com.apple.itunesapi.domain.usecase

import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.domain.repository.MoviesRepository

class SaveFavoriteMoviesUseCase(private val movieListRepository: MoviesRepository) {

    suspend fun execute(movies: Movies)=movieListRepository.saveFavoriteMovies(movies)

}