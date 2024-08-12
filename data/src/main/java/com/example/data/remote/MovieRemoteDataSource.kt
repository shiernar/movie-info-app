package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.remote.dto.movielist.MoviesDto
import com.example.data.remote.dto.searchmovie.MovieSearchResultDto
import com.example.data.wrapper.NetworkResult
import okhttp3.OkHttpClient
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMoviesList(limit: Int) : NetworkResult<MoviesDto> = handleApi { movieService.getMoviesList(limit) }

    suspend fun searchMovie(searchQuery: String) : NetworkResult<MovieSearchResultDto> = handleApi { movieService.searchMovie(searchQuery) }

    private suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if(response.code() == 204){
                    NetworkResult.Error.NetworkError(code = 204, message = response.headers()["MG-message"])
                } else {
                    NetworkResult.Success(body)
                }
            } else {
                NetworkResult.Error.NetworkError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error.NetworkError(code = e.code(), message = e.message())
        } catch (e: IOException) {
            NetworkResult.Error.IOError(message = e.message)
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
                    .header("device-datetime", getCurrentDateTime())
                    .build()
                    .let{ newRequest -> chain.proceed(newRequest) }
            }.build()

        private fun getCurrentDateTime(): String {
            val currentDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            return currentDateTime.format(formatter)
        }

    }
}