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

interface IMarvelApiClient {

    /**
     * Gets a list of marvel characters with an encapsulated response
     * @param limit max number of characters that the api is allowed to provide
     * @param offset the number characters that we want to skip
     * @return The encapsulated response with a list of characters
     */
    suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int
    ): Flow<BaseResponse<CharacterResponse>>



    /**
     * Gets a marvel character with an encapsulated response
     * @param characterId The id of the character
     * @return The encapsulated response with a single Character
     */
    suspend fun getCharacterDetail(
        characterId: Long,
    ): Flow<BaseResponse<CharacterResponse>>
}


