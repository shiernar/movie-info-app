package com.example.data.mapper

import com.example.data.remote.dto.movielist.MoviesDto
import com.example.data.remote.dto.movielist.getMoviesDtoSample
import com.example.data.remote.dto.movielist.Film
import com.example.data.remote.dto.movielist.Images
import com.example.data.remote.dto.movielist.Status
import com.example.domain.model.MovieModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MoviesDtoMapperTest {

    val images1 = mockk<Images.PosterSimple>(relaxed = true){
        every { poster.x1.medium.filmImage } returns "https://abc.jpg"
        every { still } returns mockk()
    }
    val film1 = mockk<Film>(relaxed = true){
        every { filmId } returns 1
        every { filmName } returns "Film1"
        every { releaseDates.first().releaseDate } returns "2024-01-01"
        every { images } returns images1
    }

    val images2 = mockk<Images.PosterSimple>(relaxed = true){
        every { poster.x1.medium.filmImage } returns "https://bca.jpg"
        every { still } returns mockk()
    }
    val film2 = mockk<Film>(relaxed = true){
        every { filmId } returns 2
        every { filmName } returns "Film2"
        every { releaseDates.first().releaseDate } returns "2024-02-02"
        every { images } returns images2
    }

    val status = mockk<Status>()

    @Test
    fun `Given a MovieDto object, When MoviesDtoMapper is invoked, Then returns a list of MovieModel`() {

        // Given
        val films = listOf(film1, film2)
        val moviesDto = MoviesDto(films = films, status)
        val movie1 = MovieModel(1, "Film1", "2024-01-01", "https://abc.jpg")
        val movie2 = MovieModel(2, "Film2", "2024-02-02", "https://bca.jpg")
        val expectedMovieModelList = listOf(movie1, movie2)
        // When
        val actualMovieModelList = MoviesDtoMapper().mapFrom(moviesDto)

        //Then
        assertEquals(expectedMovieModelList, actualMovieModelList)

    }

}