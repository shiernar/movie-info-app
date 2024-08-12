package com.example.data.repository

import com.example.data.mapper.MovieSearchResultDtoMapper
import com.example.data.mapper.MoviesDtoMapper
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.wrapper.NetworkResult
import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Resource
import com.example.data.wrapper.toResource
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val moviesDtoMapper : MoviesDtoMapper,
    private val movieSearchResultDtoMapper: MovieSearchResultDtoMapper
) : MovieRepository {

    override fun getMoviesList(limit: Int): Flow<Resource<List<MovieModel>>> = flow {
        emit(Resource.Loading)
        runCatching {
            val movies = movieRemoteDataSource.getMoviesList(limit)
            if(movies is NetworkResult.Success && movies.data.films.isEmpty()){
                emit(Resource.Error.DataNotFound)
            }
            emit(movies.toResource(mapper = moviesDtoMapper::mapFrom))
        }.onFailure { e -> emit(Resource.Exception(e)) }
    }.flowOn(Dispatchers.IO)

    override fun searchMovie(searchQuery: String): Flow<Resource<List<MovieModel>>> = flow {
        emit(Resource.Loading)
        runCatching {
            val movies = movieRemoteDataSource.searchMovie(searchQuery)
            if(movies is NetworkResult.Success && movies.data.films.isEmpty()){
                emit(Resource.Error.DataNotFound)
            }
            emit(movies.toResource(mapper = movieSearchResultDtoMapper::mapFrom))
        }.onFailure { e -> emit(Resource.Exception(e)) }
    }.flowOn(Dispatchers.IO)

}