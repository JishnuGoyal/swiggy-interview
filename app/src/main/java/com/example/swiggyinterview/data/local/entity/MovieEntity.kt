package com.example.swiggyinterview.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swiggyinterview.data.remote.dto.MovieDTO

@Entity(tableName = "movies")
data class MovieEntity(
    val Title: String,
    val Year: String,
    @PrimaryKey val imdbID: String,
    val Type: String,
    val Poster: String
)

fun MovieDTO.toEntity(): MovieEntity {
    return MovieEntity(
        Title = this.Title,
        Year = this.Year,
        imdbID = this.imdbID,
        Type = this.Type,
        Poster = this.Poster
    )
}
