package com.leondev7.marveltest.core.network.responses

import kotlinx.serialization.Serializable

/**
 * Marvel API character thumbnail network response.
 *
 * @param path The directory path of to the image.
 * @param extension The file extension for the image.
 */
@Serializable
data class CharacterThumbnailResponse(
    val path: String,
    val extension: String
)
