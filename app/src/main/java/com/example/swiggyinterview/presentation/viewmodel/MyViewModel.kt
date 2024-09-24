package com.example.swiggyinterview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

const val TAG = "my app"

@HiltViewModel
class MyViewModel @Inject constructor(): ViewModel(){

    private var searchJob: Job? = null

    val state = MutableStateFlow<UiState>(UiState(true))
    val statusMessage = MutableSharedFlow<String>()


}

data class UiState(val isLoading: Boolean)