package com.example.presentation.home.movielist.viewobjects

data class MovieListUiState (
    val searchQuery : String,
    val movieList : MovieListState
)

sealed interface MovieListState {

    data class Success(val movies: List<MovieVO>) : MovieListState
    sealed interface Error : MovieListState {
        data object InternetConnectionError : Error
        data class NetworkRequestError(val code: Int, val message: String?) : Error
        data object DataNotFound : Error
        data object UnknownError : Error
    }
    data object Loading : MovieListState

}