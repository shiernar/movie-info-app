package com.example.data.mapper

import com.example.data.remote.dto.movielist.Images
import com.example.data.remote.dto.movielist.MoviesDto
import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Mapper
import javax.inject.Inject

class MoviesDtoMapper @Inject constructor(): Mapper<MoviesDto, List<MovieModel>> {
    override fun mapFrom(from: MoviesDto): List<MovieModel> {
        return from.films.map { filmDto ->
            MovieModel(
                id = filmDto.filmId,
                name = filmDto.filmName,
                releaseDate = filmDto.releaseDates.first().releaseDate,
                imageUrl = when(filmDto.images) {
                        is Images.PosterSimple -> filmDto.images.poster.x1.medium.filmImage
                        is Images.PosterList -> filmDto.images.poster.getOrNull(0)?.x1?.medium?.filmImage
                        else -> null
                }
            )
        }
    }
}

