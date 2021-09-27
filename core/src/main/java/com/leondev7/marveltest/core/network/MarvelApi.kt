package com.leondev7.marveltest.core.network

import com.leondev7.marveltest.core.network.responses.BaseResponse
import com.leondev7.marveltest.core.network.responses.CharacterResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

const val HASH = "hash"
const val APIKEY = "apikey"
const val TS = "ts"

const val LIMIT = "limit"
const val OFFSET = "offset"

/**
 * Ktor client class with [CIO] for making requests
 */
class MarvelApi
constructor(
    private val httpClient: HttpClient,
    private val baseUrl: String,
    private val characterListEndpoint: String,
    private val characterEndpoint: String
) {


    /**
     * Gets a list of marvel characters with an encapsulated response
     * @param limit max number of characters that the api is allowed to provide
     * @param offset the number characters that we want to skip
     * @return The encapsulated response with a list of characters
     */
    suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int
    ): BaseResponse<CharacterResponse> {
        return httpClient.get(
            scheme = "https",
            host = baseUrl,
            port = 443,
            path = characterListEndpoint
        ) {
            parameter(LIMIT, limit)
            parameter(OFFSET, offset)
        }
    }


    /**
     * Gets a marvel character with an encapsulated response
     * @param characterId The id of the character
     * @return The encapsulated response with a single Character
     */
    suspend fun getCharacterDetail(
        characterId: Long,
    ): BaseResponse<CharacterResponse> {
        return httpClient.get(
            scheme = "https",
            host = baseUrl,
            port = 443,
            path = "$characterEndpoint/$characterId"
        )
    }
}

