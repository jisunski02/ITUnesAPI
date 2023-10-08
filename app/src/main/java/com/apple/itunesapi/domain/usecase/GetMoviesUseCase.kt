package com.apple.itunesapi.domain.usecase

import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.util.Resource
import com.apple.itunesapi.domain.repository.MoviesRepository

//Pass values here from MovieRepository
class GetMoviesUseCase(private val movieListRepository: MoviesRepository) {

    suspend fun execute(term: String, media:String, limit: String): Resource<MoviesResponse>{
        return movieListRepository.getMovies(term, media, limit)
    }
}