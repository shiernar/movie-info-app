package com.example.domain.model


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    sealed class Error<T> : Resource<T>() {
        data class NetworkError<T>(val code: Int, val message: String?) : Error<T>()
    }
    data class Exception<T>(val e: Throwable) : Resource<T>()
    data object Loading: Resource<Nothing>()
}