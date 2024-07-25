package com.example.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1(
    @SerialName("image_orientation")
    val imageOrientation: String,
    val medium: Medium,
    val region: String
)