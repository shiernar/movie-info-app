package com.example.data.wrapper

import com.example.domain.wrapper.Resource

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val message: String?) : NetworkResult<Nothing>()
    data class Exception(val e: Throwable) : NetworkResult<Nothing>()
}

fun <T, R> NetworkResult<T>.toResource(mapper: (T) -> R): Resource<R> {
    return when (this) {
        is NetworkResult.Success -> Resource.Success(mapper(this.data))
        is NetworkResult.Error -> Resource.Error.NetworkError(this.code, this.message)
        is NetworkResult.Exception -> Resource.Exception(this.e)
    }
}