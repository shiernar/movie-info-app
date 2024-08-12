package com.example.data.remote.dto.searchmovie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDate(
    val notes: String,
    @SerialName("release_date")
    val releaseDate: String
)