package com.example.swiggyinterview.domain.model.repository

import coil.network.HttpException
import com.example.swiggyinterview.data.remote.Resource
import com.example.swiggyinterview.data.remote.api.ApiService
import com.example.swiggyinterview.domain.model.Movie
import com.example.swiggyinterview.domain.model.toMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiService: ApiService){


    fun searchMovies(query: String) = flow<Resource<List<Movie>>> {
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
}