package com.example.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Still(
    @SerialName("1")
    val x1: X1X
)