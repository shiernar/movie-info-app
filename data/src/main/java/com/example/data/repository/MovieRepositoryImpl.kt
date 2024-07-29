package com.example.data.repository

import com.example.data.mapper.toMovieModelList
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.remote.dto.MoviesDto
import com.example.domain.model.MovieModel
import com.example.domain.model.Resource
import com.example.domain.model.toResource
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository{

    override suspend fun getMoviesList(limit: Int): Flow<Resource<List<MovieModel>>> = flow {
        emit(Resource.Loading)
        runCatching {
            val movies = movieRemoteDataSource.getMoviesList(limit)
            emit(movies.toResource(MoviesDto::toMovieModelList))
        }.onFailure { e -> emit(Resource.Exception(e)) }
    }.flowOn(Dispatchers.IO)

}