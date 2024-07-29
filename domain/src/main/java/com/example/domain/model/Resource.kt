package com.example.domain.model


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    sealed class Error : Resource<Nothing>() {
        data class NetworkError(val code: Int, val message: String?) : Error()
    }
    data class Exception(val e: Throwable) : Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}