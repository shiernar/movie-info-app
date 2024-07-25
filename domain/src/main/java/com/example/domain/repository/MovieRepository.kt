package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMoviesList(limit: Int) : Flow<Resource<List<Movie>>>

}