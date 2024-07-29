package com.example.data.mapper

import android.content.res.Resources.NotFoundException
import com.example.data.remote.dto.MoviesDto
import com.example.domain.model.MovieModel

fun MoviesDto.toMovieModelList() : List<MovieModel> {
    if(this.films.isEmpty()) throw NotFoundException("List of films is empty")
    return this.films.map { filmDto ->
        MovieModel(
            id = filmDto.filmId,
            name = filmDto.filmName,
            releaseDate = filmDto.releaseDates.first().releaseDate,
            imageUrl = filmDto.images.poster.x1.medium.filmImage
        )
    }
}
