package com.example.presentation.home.movielist.mapper

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Mapper
import com.example.presentation.home.movielist.viewobjects.MovieVO

class MovieVOMapper : Mapper<MovieModel, MovieVO> {
    override fun mapFrom(from: MovieModel): MovieVO = MovieVO(
        id = from.id,
        name = from.name,
        releaseDate = from.releaseDate,
        imageUrl = from.imageUrl
    )
}
