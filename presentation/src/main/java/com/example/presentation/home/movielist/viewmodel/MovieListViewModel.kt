package com.example.presentation.home.movielist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MovieModel
import com.example.domain.usecase.GetHomeMoviesListUseCase
import com.example.domain.usecase.SearchMovieUseCase
import com.example.domain.wrapper.Resource
import com.example.presentation.home.movielist.mapper.MovieVOMapper
import com.example.presentation.home.movielist.viewobjects.MovieListState
import com.example.presentation.home.movielist.viewobjects.MovieListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor (
    private val getHomeMoviesListUseCase: GetHomeMoviesListUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val movieVOMapper: MovieVOMapper
) : ViewModel() {

    private val movieListLimit = 20
    private val searchDebounceDelay : Long = 1000
    private var updatingMoviesListJob : Job? = null

    private val _uiState: MutableStateFlow<MovieListUiState> = MutableStateFlow(
        MovieListUiState(searchQuery = "", movieList = MovieListState.Loading)
    )
    val uiState get() = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            uiState.distinctUntilChangedBy { it.searchQuery }
                .map { it.searchQuery }
                .collectLatest { query ->
                    updatingMoviesListJob?.cancel()
                    delay(searchDebounceDelay)
                    updateMovieList(
                        if (query.length < 2) {
                            getHomeMoviesListUseCase(movieListLimit)
                        } else {
                            searchMovieUseCase(query)
                        }
                    )
            }
        }
    }

    private fun updateMovieList(updatingListUseCase: Flow<Resource<List<MovieModel>>>){
        updatingMoviesListJob = updatingListUseCase
            .onEach { resource ->
                _uiState.update { state -> state.copy(movieList =
                when(resource){
                    is Resource.Loading -> MovieListState.Loading
                    is Resource.Success -> MovieListState.Success(movies = resource.data.map { movieVOMapper.mapFrom(it) })
                    is Resource.Error.DataNotFound -> MovieListState.Error.DataNotFound
                    is Resource.Error.IOError -> MovieListState.Error.InternetConnectionError
                    is Resource.Error.NetworkError -> MovieListState.Error.NetworkRequestError(resource.code, resource.message)
                    is Resource.Exception -> {
                        Timber.e(resource.e)
                        MovieListState.Error.UnknownError
                    }
                })
                }
            }.launchIn(viewModelScope)
    }

    fun onQueryChange(query: String){
        _uiState.update { it.copy(searchQuery = query) }
    }

}