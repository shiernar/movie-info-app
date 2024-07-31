package com.example.data.remote.dto.movielist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDate(
    val notes: String,
    @SerialName("release_date")
    val releaseDate: String
)