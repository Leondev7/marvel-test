package com.leondev7.marveltest.core.network.responses




/**
 * Marvel API character thumbnail network response.
 *
 * @param path The directory path of to the image.
 * @param extension The file extension for the image.
 */
data class CharacterThumbnailResponse(
    val path: String,
    val extension: String
)
