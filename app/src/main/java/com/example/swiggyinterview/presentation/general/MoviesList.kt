package com.example.swiggyinterview.presentation.general

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.swiggyinterview.domain.model.Movie

@Composable
fun MoviesList(list: List<Movie>, onClick: (imdbId: String) -> Unit) {
    LazyColumn {
        items(list.size) { movieIndex ->
            GeneralCard(list[movieIndex], onClick)
        }
    }
}