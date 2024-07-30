package com.example.data.remote.serialization

import com.example.data.remote.dto.MoviesDto
import com.example.data.remote.dto.getMovieJsonStringSample
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test

class MoviesDtoSerializationTest {

    @Test
    fun `Given movies JSON String, When serialized with KotlinX Serialization, Then returns the right MoviesDto object without error`() {

        // Given
        val jsonString = MoviesDto.getMovieJsonStringSample()
        val expectedMoviesDto = MoviesDto.getMovieJsonStringSample()

        // When
        val actualMoviesDto = Json.decodeFromString<MoviesDto>(jsonString)

        //Then
        assertEquals(expectedMoviesDto, actualMoviesDto)
    }

}