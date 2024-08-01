package com.example.presentation.home.movielist.mapper

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Mapper
import com.example.presentation.home.movielist.viewobjects.MovieVO
import javax.inject.Inject

class MovieVOMapper @Inject constructor(): Mapper<MovieModel, MovieVO> {
    override fun mapFrom(from: MovieModel): MovieVO = MovieVO(
        id = from.id,
        name = from.name,
        releaseDate = from.releaseDate,
        imageUrl = from.imageUrl
    )
}
