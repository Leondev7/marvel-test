package com.leondev7.marveltest.core.network

import com.leondev7.marveltest.core.network.responses.BaseResponse
import com.leondev7.marveltest.core.network.responses.CharacterResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retry
import kotlin.coroutines.CoroutineContext

const val LIMIT = "limit"
const val OFFSET = "offset"

/**
 * Ktor client class with [CIO] for making requests
 * @param httpClient An instance of [HttpClient]
 * @param backgroundDispatcher A Coroutine dispatcher for I/O purposes
 * @param baseUrl The base url to make requests
 * @param characterListEndpoint The character list endpoint
 * @param characterEndpoint The character endpoint
 */
class MarvelApi
constructor(
    private val httpClient: HttpClient,
    private val backgroundDispatcher: CoroutineContext,
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
    ): Flow<BaseResponse<CharacterResponse>> {
        var currentDelay = 1000L
        val delayFactor = 2
        return flowOf<BaseResponse<CharacterResponse>>(httpClient.get(
            scheme = "https",
            host = baseUrl,
            port = 443,
            path = characterListEndpoint
        ) {
            parameter(LIMIT, limit)
            parameter(OFFSET, offset)
        }).flowOn(
            backgroundDispatcher
        ).retry(retries = 3) {

            delay(currentDelay)
            currentDelay = (currentDelay * delayFactor)
            return@retry true
        }
    }



    /**
     * Gets a marvel character with an encapsulated response
     * @param characterId The id of the character
     * @return The encapsulated response with a single Character
     */
    suspend fun getCharacterDetail(
        characterId: Long,
    ): Flow<BaseResponse<CharacterResponse>> =
        flowOf<BaseResponse<CharacterResponse>>(
            httpClient.get(
                scheme = "https",
                host = baseUrl,
                port = 443,
                path = "$characterEndpoint/$characterId"
            )
        ).flowOn(
            backgroundDispatcher
        ).retry(retries = 3) {
            val seconds = (1000).toLong()
            delay(seconds)
            return@retry true
        }
}


