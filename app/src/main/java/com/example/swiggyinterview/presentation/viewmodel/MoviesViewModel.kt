package com.example.swiggyinterview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiggyinterview.data.remote.Resource
import com.example.swiggyinterview.data.remote.dto.MovieDetailsDTO
import com.example.swiggyinterview.domain.model.Movie
import com.example.swiggyinterview.domain.model.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "my app"

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private var searchJob: Job? = null

    val movieListScreenState = MutableStateFlow(MovieListScreenUiState())
    val movieDetailsScreenState =
        MutableStateFlow(MovieDetailsScreenUiState(details = null, true))

    val statusMessage = MutableSharedFlow<String>()

    fun searchMovies(query: String) {
        if (query.isEmpty() || query.isEmpty()) return
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            repository.searchMovies(query).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        movieListScreenState.value =
                            MovieListScreenUiState(emptyList(), isLoading = true)
                        statusMessage.emit(result.message.toString())
                    }

                    is Resource.Loading -> {
                        movieListScreenState.value =
                            MovieListScreenUiState(emptyList(), isLoading = true)
                        statusMessage.emit(result.message.toString())
                    }

                    is Resource.Success -> {
                        movieListScreenState.value =
                            MovieListScreenUiState(result.data!!, isLoading = false)
                        statusMessage.emit("Success")
                    }
                }

            }
        }
    }

    fun getMovieDetails(imdbId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMoviesDetails(imdbId).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        movieDetailsScreenState.value = MovieDetailsScreenUiState(null, false)
                        statusMessage.emit(result.message.toString())
                    }

                    is Resource.Loading -> {
                        movieDetailsScreenState.value = MovieDetailsScreenUiState(null, false)
                        statusMessage.emit(result.message.toString())
                    }

                    is Resource.Success -> {
                        movieDetailsScreenState.value = MovieDetailsScreenUiState(result.data, false)
                        statusMessage.emit("Success")
                    }
                }

            }

        }
    }
}

data class MovieListScreenUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)

data class MovieDetailsScreenUiState(val details: MovieDetailsDTO?, val isLoading: Boolean = true)