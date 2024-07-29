package com.example.data.remote

import com.example.data.remote.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/filmsNowShowing")
    suspend fun getMoviesList(@Query("n") limit: Int) : Response<MoviesDto>

}