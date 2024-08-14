package com.example.data.remote.dto.movielist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1X(
    @SerialName("image_orientation")
    val imageOrientation: String,
    val medium: Medium
)