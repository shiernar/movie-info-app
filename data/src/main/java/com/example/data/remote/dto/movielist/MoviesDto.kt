package com.example.data.remote.dto.movielist


import kotlinx.serialization.Serializable

@Serializable
data class MoviesDto(
    val films: List<Film>,
    val status: Status
)

/**
 * Get a Movie Dto Sample equals to the JSON object returned by getMovieJsonStringSample() .
 * Used to run tests.
 */
fun MoviesDto.Companion.getMoviesDtoSample() = MoviesDto (
    films = listOf(
        Film(
            filmId = 7772,
            imdbId = 82971,
            imdbTitleId = "tt0082971",
            filmName = "Raiders of the Lost Ark",
            otherTitles = null,
            releaseDates = listOf(
                ReleaseDate(
                    releaseDate = "1992-07-01",
                    notes = "XXX"
                )
            ),
            ageRating = listOf(
                AgeRating(
                    rating = "PG ",
                    ageRatingImage = "https://assets.movieglu.com/age_rating_logos/xx/pg.png",
                    ageAdvisory = "Contains moderate violence and mild language"
                )
            ),
            filmTrailer = "https://trailer.movieglu.com/7772_high.mp4",
            synopsisLong = "As the Third Reich continues its reign of terror, Adolf Hitler is on a quest for the legendary Ark os the Covenenant- resting place of the Ten Commandments- whose supernatural powers, legend says, can wipe out entire armies.\n\nThe U.S. Government turns to Dr. Indiana Jones, for the mission.  Relentlessly pursued by Hitler's henchmen, Indy infiltrartes their massive digging operation in a race against time to discover the Well od the Souls, where the Ark has lain undisturbed for centuries.",
            images = Images(
                poster = Poster( x1 = X1(
                    imageOrientation = "portrait",
                    region = "UK",
                    medium = Medium(
                        filmImage = "https://image.movieglu.com/7772/GBR_007772h0.jpg",
                        width = 200,
                        height = 300
                    )
                )
                ),
                still = Still( x1 = X1X(
                    imageOrientation = "landscape",
                    medium = Medium(
                        filmImage = "https://image.movieglu.com/7772/007772h2.jpg",
                        width = 300,
                        height = 200
                    ),
                )
                )
            )
        ),
        Film(
            filmId = 184126,
            imdbId = 3659388,
            imdbTitleId = "tt3659388",
            filmName = "The Martian",
            otherTitles = null,
            releaseDates = listOf(
                ReleaseDate(
                    releaseDate = "2015-09-30",
                    notes = "XXX"
                )
            ),
            ageRating = listOf(
                AgeRating(
                    rating = "12A ",
                    ageRatingImage = "https://assets.movieglu.com/age_rating_logos/xx/12a.png",
                    ageAdvisory = "infrequent strong language, injury detail"
                )
            ),
            filmTrailer = "https://trailer.movieglu.com/184126_uk_high.mp4",
            synopsisLong = "During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive. Millions of miles away, NASA and a team of international scientists work tirelessly to bring \"the Martian\" home, while his crewmates concurrently plot a daring, if not impossible, rescue mission. As these stories of incredible bravery unfold, the world comes together to root for Watney's safe return.",
            images = Images(
                poster = Poster( X1 (
                    imageOrientation = "portrait",
                    region = "UK",
                    medium = Medium(
                        filmImage = "https://image.movieglu.com/184126/GBR_184126h0.jpg",
                        width = 200,
                        height = 300
                    )
                )
                ),
                still = Still( X1X(
                    imageOrientation = "landscape",
                    medium = Medium(
                        filmImage = "https://image.movieglu.com/184126/184126h2.jpg",
                        width = 300,
                        height = 200
                    )
                )
                )
            )
        )
    ),
    status = Status(
        count = 2,
        state = "OK",
        method = "filmsNowShowing",
        message = null,
        requestMethod = "GET",
        version = "PERS_196_XXv200",
        territory = "XX",
        deviceDatetimeSent = "2024-07-29T09:26:05.204Z",
        deviceDatetimeUsed = "2024-07-29 09:26:05"
    )
)

/**
 * Get a Movie JSON String object equivalent to the MovieDto returned by getMoviesDtoSample() .
 * Used to run tests.
 */
fun MoviesDto.Companion.getMovieJsonStringSample() : String =
 """
        {
            "films": [
                {
                    "film_id": 7772,
                    "imdb_id": 82971,
                    "imdb_title_id": "tt0082971",
                    "film_name": "Raiders of the Lost Ark",
                    "other_titles": null,
                    "release_dates": [
                        {
                            "release_date": "1992-07-01",
                            "notes": "XXX"
                        }
                    ],
                    "age_rating": [
                        {
                            "rating": "PG ",
                            "age_rating_image": "https://assets.movieglu.com/age_rating_logos/xx/pg.png",
                            "age_advisory": "Contains moderate violence and mild language"
                        }
                    ],
                    "film_trailer": "https://trailer.movieglu.com/7772_high.mp4",
                    "synopsis_long": "As the Third Reich continues its reign of terror, Adolf Hitler is on a quest for the legendary Ark os the Covenenant- resting place of the Ten Commandments- whose supernatural powers, legend says, can wipe out entire armies.\n\nThe U.S. Government turns to Dr. Indiana Jones, for the mission.  Relentlessly pursued by Hitler's henchmen, Indy infiltrartes their massive digging operation in a race against time to discover the Well od the Souls, where the Ark has lain undisturbed for centuries.",
                    "images": {
                        "poster": {
                            "1": {
                                "image_orientation": "portrait",
                                "region": "UK",
                                "medium": {
                                    "film_image": "https://image.movieglu.com/7772/GBR_007772h0.jpg",
                                    "width": 200,
                                    "height": 300
                                }
                            }
                        },
                        "still": {
                            "1": {
                                "image_orientation": "landscape",
                                "medium": {
                                    "film_image": "https://image.movieglu.com/7772/007772h2.jpg",
                                    "width": 300,
                                    "height": 200
                                }
                            }
                        }
                    }
                },
                {
                    "film_id": 184126,
                    "imdb_id": 3659388,
                    "imdb_title_id": "tt3659388",
                    "film_name": "The Martian",
                    "other_titles": null,
                    "release_dates": [
                        {
                            "release_date": "2015-09-30",
                            "notes": "XXX"
                        }
                    ],
                    "age_rating": [
                        {
                            "rating": "12A ",
                            "age_rating_image": "https://assets.movieglu.com/age_rating_logos/xx/12a.png",
                            "age_advisory": "infrequent strong language, injury detail"
                        }
                    ],
                    "film_trailer": "https://trailer.movieglu.com/184126_uk_high.mp4",
                    "synopsis_long": "During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew. But Watney has survived and finds himself stranded and alone on the hostile planet. With only meager supplies, he must draw upon his ingenuity, wit and spirit to subsist and find a way to signal to Earth that he is alive. Millions of miles away, NASA and a team of international scientists work tirelessly to bring \"the Martian\" home, while his crewmates concurrently plot a daring, if not impossible, rescue mission. As these stories of incredible bravery unfold, the world comes together to root for Watney's safe return.",
                    "images": {
                        "poster": {
                            "1": {
                                "image_orientation": "portrait",
                                "region": "UK",
                                "medium": {
                                    "film_image": "https://image.movieglu.com/184126/GBR_184126h0.jpg",
                                    "width": 200,
                                    "height": 300
                                }
                            }
                        },
                        "still": {
                            "1": {
                                "image_orientation": "landscape",
                                "medium": {
                                    "film_image": "https://image.movieglu.com/184126/184126h2.jpg",
                                    "width": 300,
                                    "height": 200
                                }
                            }
                        }
                    }
                }
            ],
            "status": {
                "count": 2,
                "state": "OK",
                "method": "filmsNowShowing",
                "message": null,
                "request_method": "GET",
                "version": "PERS_196_XXv200",
                "territory": "XX",
                "device_datetime_sent": "2024-07-29T09:26:05.204Z",
                "device_datetime_used": "2024-07-29 09:26:05"
            }
        }
    """.trimIndent()