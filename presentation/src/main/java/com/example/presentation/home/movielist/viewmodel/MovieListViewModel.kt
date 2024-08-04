package com.example.presentation.home.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetHomeMoviesListUseCase
import com.example.domain.wrapper.Resource
import com.example.presentation.home.movielist.mapper.MovieVOMapper
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor (
    private val getHomeMoviesListUseCase: GetHomeMoviesListUseCase,
    private val movieVOMapper: MovieVOMapper
) : ViewModel() {

    private val movieListLimit = 20

    private val _uiState: MutableStateFlow<MovieListUiState> = MutableStateFlow(
        MovieListUiState.Loading
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchMovieListData()
    }

    fun fetchMovieListData(){
        getHomeMoviesListUseCase(movieListLimit)
            .onEach { resource ->
                _uiState.update {
                    when(resource){
                        is Resource.Loading -> MovieListUiState.Loading
                        is Resource.Success -> MovieListUiState.Success(movies = resource.data.map { movieVOMapper.mapFrom(it) })
                        is Resource.Error.DataNotFound -> MovieListUiState.Error.DataNotFound
                        is Resource.Error.IOError -> MovieListUiState.Error.InternetConnectionError
                        is Resource.Error.NetworkError -> MovieListUiState.Error.NetworkRequestError(resource.code, resource.message)
                        is Resource.Exception -> {
                            Timber.e(resource.e)
                            MovieListUiState.Error.UnknownError
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

}