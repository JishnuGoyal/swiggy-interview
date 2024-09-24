package com.example.swiggyinterview.domain.model.repository

import coil.network.HttpException
import com.example.swiggyinterview.data.remote.Resource
import com.example.swiggyinterview.data.remote.api.ApiService
import com.example.swiggyinterview.data.remote.dto.MovieDetailsDTO
import com.example.swiggyinterview.domain.model.Movie
import com.example.swiggyinterview.domain.model.toMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: ApiService){


    fun searchMovies(query: String) = flow<Resource<List<Movie>>> {
        if (query.isEmpty() || query.isEmpty()) return@flow
        emit(Resource.Loading())

        try {
            val apiData = apiService.searchMovies(query)
            val movies = apiData.Search.map { it.toMovie() }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error("check you internet" + e.message, emptyList()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error", emptyList()))
        }
    }

    fun getMoviesDetails(imdbId: String) = flow<Resource<MovieDetailsDTO>> {
        emit(Resource.Loading())

        try {
            val apiData = apiService.getMovieDetails(imdbId)
            emit(Resource.Success(apiData))
        } catch (e: HttpException) {
            emit(Resource.Error("check you internet" + e.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}