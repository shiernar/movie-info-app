package com.example.data.remote.dto.movielist


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerialName("age_rating")
    val ageRating: List<AgeRating>,
    @SerialName("film_id")
    val filmId: Int,
    @SerialName("film_name")
    val filmName: String,
    @SerialName("film_trailer")
    val filmTrailer: String?,
    val images: Images,
    @SerialName("imdb_id")
    val imdbId: Int,
    @SerialName("imdb_title_id")
    val imdbTitleId: String,
    @SerialName("other_titles")
    val otherTitles: OtherTitles?,
    @SerialName("release_dates")
    val releaseDates: List<ReleaseDate>,
    @SerialName("synopsis_long")
    val synopsisLong: String
)