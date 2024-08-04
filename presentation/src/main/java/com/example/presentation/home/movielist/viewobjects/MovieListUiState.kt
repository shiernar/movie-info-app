package com.example.presentation.home.movielist.viewobjects

sealed interface MovieListUiState {
    data class Success(val movies : List<MovieVO>) : MovieListUiState
    sealed interface Error : MovieListUiState {
        data object InternetConnectionError : Error
        data class NetworkRequestError(val code: Int, val message: String?) : Error
        data object DataNotFound : Error
        data object UnknownError : Error
    }
    data object Loading : MovieListUiState
}