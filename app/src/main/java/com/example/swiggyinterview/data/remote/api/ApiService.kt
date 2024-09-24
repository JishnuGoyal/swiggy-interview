package com.example.swiggyinterview.data.remote.api

import com.example.swiggyinterview.api_key
import com.example.swiggyinterview.data.remote.dto.MovieDetailsDTO
import com.example.swiggyinterview.data.remote.dto.MovieSearchResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = api_key
    ): MovieSearchResponseDTO

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") query: String,
        @Query("apiKey") apiKey: String = api_key
    ): MovieDetailsDTO
}