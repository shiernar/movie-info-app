package com.example.presentation.home.movielist.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.home.movielist.viewmodel.MovieListViewModel
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import com.example.presentation.home.movielist.viewobjects.MovieVO

@Composable
fun MovieListScreen(
    onMovieItemClicked: (movieId : Int) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    viewModel.fetchMovieListData()
    val uiState by viewModel.uiState.collectAsState()
    MovieListScreenContent(uiState, onMovieItemClicked)
}

@Composable
fun MovieListScreenContent(
    uiState: MovieListUiState,
    onMovieItemClicked: (movieId : Int) -> Unit,
) {
    when(uiState) {
        is MovieListUiState.Error -> Text("Error")
        is MovieListUiState.Loading -> CircularProgressIndicator()
        is MovieListUiState.Success -> MovieList(uiState.movies, onMovieItemClicked)
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