package com.example.data.remote

import com.example.data.remote.dto.movielist.MoviesDto
import com.example.data.remote.dto.searchmovie.MovieSearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {

    @GET("/filmsNowShowing")
    suspend fun getMoviesList(@Query("n") limit: Int) : Response<MoviesDto>

    @GET("/filmLiveSearch")
    suspend fun searchMovie(@Query("query") query: String) : Response<MovieSearchResultDto>

}