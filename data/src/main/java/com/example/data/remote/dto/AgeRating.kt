package com.example.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgeRating(
    @SerialName("age_advisory")
    val ageAdvisory: String,
    @SerialName("age_rating_image")
    val ageRatingImage: String,
    val rating: String
)