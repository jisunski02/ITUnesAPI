package com.apple.itunesapi.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.apple.itunesapi.data.model.Movies
import com.apple.itunesapi.data.model.MoviesResponse
import com.apple.itunesapi.data.util.Resource
import com.apple.itunesapi.domain.usecase.GetMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSavedFavoriteMoviesUseCase
import com.apple.itunesapi.domain.usecase.GetSearchedMoviesUseCase
import com.apple.itunesapi.domain.usecase.SaveFavoriteMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//pass any instance of usecase here related to movies as a constructor parameter
class MoviesViewModel(
    private val app: Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
    private val saveFavoriteMoviesUseCase: SaveFavoriteMoviesUseCase,
    private val getSavedFavoriteMoviesUseCase: GetSavedFavoriteMoviesUseCase
): AndroidViewModel(app) {

    val movieList: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun getMovies(term: String, media: String, limit: String) = viewModelScope.launch(Dispatchers.IO) {
            movieList.postValue(Resource.Loading())

        try{
                if(isNetworkAvailable(app)){
                    val movieResult = getMoviesUseCase.execute(term, media, limit)
                    movieList.postValue(movieResult)
                }
                else{
                    movieList.postValue(Resource.Error("No Internet Connection"))
                }
            }

            catch (e: Exception){
                movieList.postValue(Resource.Error(e.toString()))
            }

    }

    //Search
    val searchMovies: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun searchMovies(term: String, media: String, limit: String) =viewModelScope.launch {
        searchMovies.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)){
                val movieSearchResult = getSearchedMoviesUseCase.execute(term, media, limit)
                searchMovies.postValue(movieSearchResult)
            }
            else{
                searchMovies.postValue(Resource.Error("No Internet Connection"))
            }
        }

        catch (e: Exception){
            searchMovies.postValue(Resource.Error(e.toString()))
        }
    }

    //save movie to local db
    fun saveFavoriteMovie(movies: Movies) = viewModelScope.launch {
        saveFavoriteMoviesUseCase.execute(movies)
    }

    //get saved movies from local db

    fun getSavedFavoriteMovie() = liveData{
        getSavedFavoriteMoviesUseCase.execute().collect{
            emit(it)
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
}