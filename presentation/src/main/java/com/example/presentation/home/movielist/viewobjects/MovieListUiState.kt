package com.example.presentation.home.movielist.viewobjects

sealed interface MovieListUiState {
    data class Success(val movies : List<MovieVO>) : MovieListUiState
    data object Error : MovieListUiState
    data object Loading : MovieListUiState
}