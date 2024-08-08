package com.example.presentation.home.movielist.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.ui.theme.dimensions
import com.example.presentation.home.movielist.components.MovieListItem
import com.example.presentation.home.movielist.viewmodel.MovieListViewModel
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import com.example.presentation.home.movielist.viewobjects.MovieVO

@Composable
fun MovieListScreen(
    onMovieItemClicked: (movieId : Int) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MovieListScreenContent(uiState, onMovieItemClicked)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreenContent(
    uiState: MovieListUiState,
    onMovieItemClicked: (movieId : Int) -> Unit,
) {
    Scaffold(
        /*topBar = {
            SearchBar(modifier = Modifier., query = "Search", onQueryChange = {}, onSearch = {}, active = true, onActiveChange = {}) {

            }
        }*/
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is MovieListUiState.Error -> Text("Error : $uiState")
                is MovieListUiState.Loading -> CircularProgressIndicator()
                is MovieListUiState.Success -> MovieList(uiState.movies, onMovieItemClicked)
            }
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
            MovieListItem(movieVO = movie, onMovieItemClicked = onMovieItemClicked)
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimensions.paddingLarge),
                color = MaterialTheme.colorScheme.primaryContainer,
                thickness = 1.dp,
            )
        }
    }
}