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
import kotlinx.coroutines.flow.*
import java.io.IOException
import kotlin.coroutines.CoroutineContext

const val LIMIT = "limit"
const val OFFSET = "offset"
const val SECURE_SCHEME = "https"

/**
 * Ktor client class with [CIO] for making requests
 * @param httpClient An instance of [HttpClient]
 * @param backgroundDispatcher A Coroutine dispatcher for I/O purposes
 * @param baseUrl The base url to make requests
 * @param characterListEndpoint The character list endpoint
 * @param characterEndpoint The character endpoint
 */
class MarvelApiClient
constructor(
    private val retryPolicy : RetryPolicy,
    private val httpClient: HttpClient,
    private val backgroundDispatcher: CoroutineContext,
    private val baseUrl: String,
    private val characterListEndpoint: String,
    private val characterEndpoint: String
) : IMarvelApiClient , BaseApiClient(){

    /**
     * Gets a list of marvel characters with an encapsulated response
     * @param limit max number of characters that the api is allowed to provide
     * @param offset the number characters that we want to skip
     * @return The encapsulated response with a list of characters
     */
    override suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int
    ): Flow<BaseResponse<CharacterResponse>> = safeApiCall(
        backgroundDispatcher = backgroundDispatcher,
        retryPolicy = retryPolicy,
        apiCall = httpClient.get(
            scheme = SECURE_SCHEME,
            host = baseUrl,
            path = characterListEndpoint
        ) {
            parameter(LIMIT, limit)
            parameter(OFFSET, offset)
        }
    )

    /**
     * Gets a marvel character with an encapsulated response
     * @param characterId The id of the character
     * @return The encapsulated response with a single Character
     */
    override suspend fun getCharacterDetail(
        characterId: Long,
    ): Flow<BaseResponse<CharacterResponse>> = safeApiCall(
        backgroundDispatcher = backgroundDispatcher,
        retryPolicy = retryPolicy,
        apiCall =  httpClient.get(
            scheme = SECURE_SCHEME,
            host = baseUrl,
            path = String.format(characterEndpoint,characterId)
        )
    )


}


