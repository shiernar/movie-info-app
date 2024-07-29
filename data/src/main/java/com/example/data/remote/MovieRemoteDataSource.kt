package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.dto.MoviesDto
import com.example.domain.model.NetworkResult
import com.example.domain.model.Resource
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import java.util.Date
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
                if(response.code() == 204){
                    NetworkResult.Error(code = 204, message = response.headers()["MG-message"])
                } else {
                    NetworkResult.Success(body)
                }
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }

    companion object {

        fun movieApiHeadersInterceptor() : OkHttpClient =
            OkHttpClient.Builder().addInterceptor { chain ->
                chain.request().newBuilder()
                    .header("client","PERS_196")
                    .header("x-api-key", BuildConfig.MOVIE_API_KEY)
                    .header("authorization", BuildConfig.MOVIE_API_AUTH)
                    .header("territory","XX")
                    .header("api-version","v200")
                    .header("geolocation","LAT;LON")
                    .header("device-datetime","2018-09-20T13:09:34.927Z")
                    .build()
                    .let{ newRequest -> chain.proceed(newRequest) }
            }.build()

    }
}