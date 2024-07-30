package com.example.data.mapper

import com.example.data.remote.dto.MoviesDto
import com.example.data.remote.dto.getMoviesDtoSample
import com.example.domain.model.MovieModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MoviesDtoMapperTest {

    @Test
    fun `Given a MovieDto object, When MoviesDtoMapper is invoked, Then returns a list of MovieModel`() {

        // Given
        val movieDto = MoviesDto.getMoviesDtoSample()
        val movie1 = movieDto.films[0].let{ MovieModel(it.filmId, it.filmName, it.releaseDates.first().releaseDate, it.images.poster.x1.medium.filmImage) }
        val movie2 = movieDto.films[1].let{ MovieModel(it.filmId, it.filmName, it.releaseDates.first().releaseDate, it.images.poster.x1.medium.filmImage) }
        val expectedMovieModelList = listOf(movie1, movie2)

        // When
        val actualMovieModelList = MoviesDtoMapper().mapFrom(movieDto)

        //Then
        assertEquals(expectedMovieModelList, actualMovieModelList)

    }

}