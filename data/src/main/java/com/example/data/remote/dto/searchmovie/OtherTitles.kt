package com.example.data.remote.dto.searchmovie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherTitles(
    @SerialName("EN")
    val eN: String
)