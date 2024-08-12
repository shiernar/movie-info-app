package com.example.data.remote.dto.searchmovie

import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResultDto(
    val films: List<Film>,
    val status: Status
)