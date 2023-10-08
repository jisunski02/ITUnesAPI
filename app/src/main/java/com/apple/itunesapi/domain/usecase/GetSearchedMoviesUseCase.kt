package com.apple.itunesapi.domain.usecase

import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.util.Resource
import com.apple.itunesapi.domain.repository.MoviesRepository

class GetSearchedMoviesUseCase(private val movieListRepository: MoviesRepository) {

    suspend fun execute(term: String, media: String, limit: String): Resource<MoviesResponse> {
        return movieListRepository.getSearchedMovies(term, media, limit)
    }
}