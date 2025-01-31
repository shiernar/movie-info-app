package com.example.di.module

import com.example.data.BuildConfig
import com.example.data.mapper.MovieSearchResultDtoMapper
import com.example.data.mapper.MoviesDtoMapper
import com.example.data.remote.MovieRemoteDataSource
import com.example.data.remote.MovieService
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMovieService() : MovieService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIE_API_BASE_URL)
            .client(MovieRemoteDataSource.movieApiHeadersInterceptor())
            .addConverterFactory(
                Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    isLenient = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource, moviesDtoMapper: MoviesDtoMapper, searchMovieDtoMapper: MovieSearchResultDtoMapper) : MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, moviesDtoMapper, searchMovieDtoMapper)
    }

}