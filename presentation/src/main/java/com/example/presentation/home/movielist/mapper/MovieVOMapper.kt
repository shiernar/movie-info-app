package com.example.presentation.home.movielist.mapper

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Mapper
import com.example.presentation.home.movielist.viewobjects.MovieVO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class MovieVOMapper @Inject constructor(): Mapper<MovieModel, MovieVO> {
    override fun mapFrom(from: MovieModel): MovieVO = MovieVO(
        id = from.id,
        name = from.name,
        releaseDate = from.releaseDate?.toDateVOFormat(),
        imageUrl = from.imageUrl
    )
}

fun String.toDateVOFormat() : String = runCatching {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.US)
    val date = LocalDate.parse(this, inputFormatter)
    date.format(outputFormatter)
}.getOrElse { this }