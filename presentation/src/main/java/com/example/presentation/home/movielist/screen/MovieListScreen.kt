package com.example.presentation.home.movielist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.home.movielist.viewmodel.MovieListViewModel
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import com.example.presentation.home.movielist.viewobjects.MovieVO

@Composable
fun MovieListScreen(
    onMovieItemClicked: (movieId : Int) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MovieListScreenContent(uiState, onMovieItemClicked, onRefresh = { viewModel.fetchMovieListData() })
}

@Composable
fun MovieListScreenContent(
    uiState: MovieListUiState,
    onMovieItemClicked: (movieId : Int) -> Unit,
    onRefresh : () -> Unit
) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onRefresh) {
            Text(text = "Refresh")
        }
        when (uiState) {
            is MovieListUiState.Error -> Text("Error : $uiState")
            is MovieListUiState.Loading -> CircularProgressIndicator()
            is MovieListUiState.Success -> MovieList(uiState.movies, onMovieItemClicked)
        }
    }
}

@Composable
fun MovieList(
    movies : List<MovieVO>,
    onMovieItemClicked: (movieId : Int) -> Unit
) {
    LazyColumn {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            Text(movie.toString())
        }
    }
}