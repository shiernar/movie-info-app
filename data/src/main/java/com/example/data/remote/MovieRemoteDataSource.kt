package com.example.data.remote

import com.example.data.remote.dto.Movies
import com.example.domain.model.Resource
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMoviesList(limit: Int) : Resource<Movies> = handleApi { movieService.getMoviesList(limit) }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Resource.Success(body)
            } else {
                Resource.Error.NetworkError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            Resource.Error.NetworkError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            Resource.Exception(e)
        }
    }
}