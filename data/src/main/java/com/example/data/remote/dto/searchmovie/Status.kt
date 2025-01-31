package com.example.data.remote.dto.searchmovie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val count: Int,
    @SerialName("device_datetime_sent")
    val deviceDatetimeSent: String,
    @SerialName("device_datetime_used")
    val deviceDatetimeUsed: String,
    val message: String?,
    val method: String,
    @SerialName("request_method")
    val requestMethod: String,
    val state: String,
    val territory: String,
    val version: String
)