package com.example.domain.usecase

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Resource
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(searchQuery: String): Flow<Resource<List<MovieModel>>> = repository.searchMovie(searchQuery)
}