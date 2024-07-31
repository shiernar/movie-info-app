package com.example.data.remote.dto.movielist


import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val poster: Poster,
    val still: Still?
)