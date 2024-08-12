package com.example.domain.repository

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Resource
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(limit: Int) : Flow<Resource<List<MovieModel>>>

    fun searchMovie(searchQuery: String) : Flow<Resource<List<MovieModel>>>

}