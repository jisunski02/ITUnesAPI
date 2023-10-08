package com.example.itunessearchapi.data.repository

import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.repository.dataSource.MoviesLocalDataSource
import com.apple.itunesapi.data.repository.dataSource.MoviesRemoteDataSource
import com.apple.itunesapi.data.util.Resource
import com.apple.itunesapi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

//Middleware for MoviesRepository

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
): MoviesRepository {

    private fun responseToResource(response: Response<MoviesResponse>): Resource<MoviesResponse>{
        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovies(term: String, media:String, limit: String): Resource<MoviesResponse> {
        return responseToResource(moviesRemoteDataSource.getMovies(term, media, limit))
    }

    override suspend fun getSearchedMovies(
        term: String,
        media: String,
        limit: String
    ): Resource<MoviesResponse> {
        return responseToResource(moviesRemoteDataSource.getSearchedMovies(term, media, limit))
    }


    override suspend fun saveFavoriteMovies(movies: Movies) {
        moviesLocalDataSource.saveFavoriteMoviesToDB(movies)
    }

    override fun getSavedFavoriteMovies(): Flow<List<Movies>> {
        return moviesLocalDataSource.getSavedFavoriteMovies()
    }
}