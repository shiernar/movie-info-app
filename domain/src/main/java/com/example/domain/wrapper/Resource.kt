package com.example.domain.wrapper


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Exception(val e: Throwable) : Resource<Nothing>()
    data object Loading: Resource<Nothing>()
    sealed class Error : Resource<Nothing>() {
        data class NetworkError(val code: Int, val message: String?) : Error()
        data object DataNotFound : Error()
    }
}