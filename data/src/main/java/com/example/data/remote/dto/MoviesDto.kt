package com.example.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val films: List<Film>,
    val status: Status
)