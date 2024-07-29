package com.example.data.remote

import com.example.data.remote.dto.MoviesDto
import com.example.domain.model.NetworkResult
import com.example.domain.model.Resource
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMoviesList(limit: Int) : NetworkResult<MoviesDto> = handleApi { movieService.getMoviesList(limit) }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }
}