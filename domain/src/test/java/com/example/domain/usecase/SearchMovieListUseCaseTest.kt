package com.example.domain.usecase

import com.example.domain.model.MovieModel
import com.example.domain.wrapper.Resource
import com.example.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.concurrent.TimeoutException
import kotlin.random.Random

class SearchMovieListUseCaseTest {

    private val movieRepository : MovieRepository = mockk()
    private val searchMovieUseCase = SearchMovieUseCase(movieRepository)
    private val query : String = "query"
    private val limit : Int = 10

    @Test
    fun `Given repository gives a list a films resulting the search query, When SearchMovieListUseCaseTest is invoked, Then returns the corresponding list of MovieModel`() = runTest {
        // Given
        val expectedMovies = List(limit) {
            MovieModel(Random.nextInt(), Random.nextInt().toString(), Random.nextInt().toString(), Random.nextInt().toString())
        }
        coEvery { movieRepository.searchMovie(query) } returns flowOf(Resource.Success(expectedMovies))

        // When
        val flowResult = searchMovieUseCase.invoke(query).single()
        val actualMovies : List<MovieModel>? = if(flowResult is Resource.Success) flowResult.data else null

        // Then
        assertEquals(expectedMovies, actualMovies)
    }

    @Test
    fun `Given repository gives an error, When SearchMovieListUseCaseTest is invoked, Then returns the error`() = runTest {
        // Given
        coEvery { movieRepository.searchMovie(query) } returns flowOf(Resource.Error.NetworkError(code = 404, "Error"))

        // When
        val flowResult = searchMovieUseCase.invoke(query).single()

        // Then
        assert(flowResult is Resource.Error.NetworkError)
        if(flowResult is Resource.Error.NetworkError) {
            assertEquals(flowResult.code, 404)
            assertEquals(flowResult.message, "Error")
        }
    }

    @Test
    fun `Given repository gives a loading resource state, When SearchMovieListUseCaseTest is invoked, Then returns a loading resource object`() = runTest {
        // Given
        coEvery { movieRepository.searchMovie(query) } returns flowOf(Resource.Loading)

        // When
        val flowResult = searchMovieUseCase.invoke(query).single()

        // Then
        assert(flowResult is Resource.Loading)
    }

    @Test
    fun `Given repository gives an exception resource object, When SearchMovieListUseCaseTest is invoked, Then returns then an exception resource object`() = runTest {
        // Given
        coEvery { movieRepository.searchMovie(query) } returns flowOf(Resource.Exception(TimeoutException()))

        // When
        val flowResult = searchMovieUseCase.invoke(query).single()

        // Then
        assert(flowResult is Resource.Exception)
        if(flowResult is Resource.Exception) {
            assert(flowResult.e is TimeoutException)
        }
    }

}