package com.example.data.remote.dto.movielist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherTitles(
    @SerialName("EN")
    val eN: String
)