package com.example.presentation.home.movielist.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.common.ui.theme.dimensions
import com.example.presentation.R
import com.example.presentation.home.movielist.components.MovieListItem
import com.example.presentation.home.movielist.components.MovieSearchBar
import com.example.presentation.home.movielist.viewmodel.MovieListViewModel
import com.example.presentation.home.movielist.viewobjects.MovieListState
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import com.example.presentation.home.movielist.viewobjects.MovieVO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieListScreen(
    onMovieItemClicked: (movieId : Int) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MovieListScreenContent(
        uiState.movieList,
        uiState.searchQuery,
        onMovieItemClicked,
        onQueryChange = { query -> viewModel.onQueryChange(query) }
    )
}

@Composable
fun MovieListScreenContent(
    movieListState: MovieListState,
    searchQuery: String,
    onMovieItemClicked: (movieId : Int) -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                MovieSearchBar(
                    query = searchQuery,
                    onQueryChange = onQueryChange
                )
            }
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (movieListState) {
                is MovieListState.Error -> ErrorMessage(error = movieListState)
                is MovieListState.Loading -> CircularProgressIndicator()
                is MovieListState.Success -> MovieList(movieListState.movies, onMovieItemClicked)
            }
        }
    }
}

@Composable
fun MovieList(
    movies : List<MovieVO>,
    onMovieItemClicked: (movieId : Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = movies,
            key = { movie -> movie.id }
        ) { movie ->
            MovieListItem(movieVO = movie, onMovieItemClicked = onMovieItemClicked)
        }
    }
}

@Composable
fun ErrorMessage(error: MovieListState.Error){
    Text(text = when(error) {
        MovieListState.Error.DataNotFound -> stringResource(R.string.data_not_found_error)
        MovieListState.Error.InternetConnectionError -> stringResource(R.string.internet_connection_error)
        is MovieListState.Error.NetworkRequestError -> stringResource(R.string.network_request_error, error.code)
        MovieListState.Error.UnknownError -> stringResource(R.string.unknown_error)
    })
}