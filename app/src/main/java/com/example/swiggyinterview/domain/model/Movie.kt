package com.example.swiggyinterview.domain.model

import com.example.swiggyinterview.data.remote.dto.MovieDTO

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)

fun MovieDTO.toMovie(): Movie {
    return Movie(
        Title = this.Title,
        Year = this.Year,
        imdbID = this.imdbID,
        Type = this.Type,
        Poster = this.Poster
    )
}
