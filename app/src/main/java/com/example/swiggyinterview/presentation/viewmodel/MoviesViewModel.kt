package com.example.swiggyinterview.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiggyinterview.data.remote.Resource
import com.example.swiggyinterview.data.remote.api.ApiService
import com.example.swiggyinterview.domain.model.Movie
import com.example.swiggyinterview.domain.model.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    val state = MutableStateFlow<UiState>(UiState())
    val statusMessage = MutableSharedFlow<String>()

    fun searchMovies(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            repository.searchMovies(query).collect { result ->
                when(result) {
                    is Resource.Error -> {
                        state.value = UiState(emptyList(), isLoading = true)
                        statusMessage.emit(result.message.toString())
                    }
                    is Resource.Loading -> {
                        state.value = UiState(emptyList(), isLoading = true)
                        statusMessage.emit(result.message.toString())
                    }
                    is Resource.Success -> {
                        state.value = UiState(result.data!!, isLoading = false)
                        statusMessage.emit("Success")
                    }
                }

            }
        }
    }
}

data class UiState(val movies: List<Movie> = emptyList(), val isLoading: Boolean = false)