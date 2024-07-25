package com.example.data.repository

import com.example.data.remote.MovieRemoteDataSource
import com.example.domain.model.Movie
import com.example.domain.model.Resource
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository{

    override suspend fun getMoviesList(limit: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading)
        movieRemoteDataSource.getMoviesList(limit)
        TODO("Movie mapper")
    }

}