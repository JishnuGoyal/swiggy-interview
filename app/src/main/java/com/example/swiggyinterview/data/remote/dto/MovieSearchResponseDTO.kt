package com.example.swiggyinterview.data.remote.dto

data class MovieSearchResponseDTO(
    val Search: List<MovieDTO>,
    val totalResults: String,
    val Response: String
)

data class MovieDTO(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)
