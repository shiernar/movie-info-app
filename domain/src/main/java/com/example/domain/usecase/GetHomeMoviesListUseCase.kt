package com.example.domain.usecase

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Resource
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeMoviesListUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(limit :Int): Flow<Resource<List<MovieModel>>> = repository.getMoviesList(limit)
}