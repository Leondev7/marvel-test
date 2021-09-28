package com.leondev7.marveltest.core.network.responses

/**
 * Marvel API character network response item.
 *
 * @param id The unique ID of the character resource.
 * @param name The name of the character.
 * @param description A short bio or description of the character.
 * @param thumbnail The representative image for this character.
 */
data class CharacterResponse(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: CharacterThumbnailResponse
)
