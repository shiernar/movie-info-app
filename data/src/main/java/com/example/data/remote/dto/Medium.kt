package com.example.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Medium(
    @SerialName("film_image")
    val filmImage: String,
    val height: Int,
    val width: Int
)