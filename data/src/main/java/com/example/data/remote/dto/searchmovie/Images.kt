package com.example.data.remote.dto.searchmovie

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable(ImagesSerializer::class)
sealed class Images {
    @Serializable
    data class PosterList(val poster: List<Poster>, val still: Still?) : Images()
    @Serializable
    data class PosterSimple(val poster: Poster, val still: Still?) : Images()
}

object ImagesSerializer : JsonContentPolymorphicSerializer<Images>(
    Images::class,
) {
    override fun selectDeserializer(
        element: JsonElement,
    ): DeserializationStrategy<Images> {
        val jsonObject = element.jsonObject
        return when {
            jsonObject.containsKey("poster") && jsonObject["poster"] is JsonArray -> Images.PosterList.serializer()
            jsonObject.containsKey("poster") && jsonObject["poster"] is JsonObject -> Images.PosterSimple.serializer()
            else -> throw IllegalArgumentException(
                "Unsupported Images type.",
            )
        }
    }
}