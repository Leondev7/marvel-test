package com.leondev7.marveltest.core.network

import com.leondev7.marveltest.core.extensions.md5
import com.leondev7.marveltest.core.network.responses.BaseResponse
import com.leondev7.marveltest.core.network.responses.CharacterResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

const val HASH = "hash"
const val APIKEY = "apikey"
const val TS = "ts"

const val LIMIT = "limit"
const val OFFSET = "offset"

/**
 * Ktor client class with [CIO] for making requests
 */
class MarvelApi
@Inject constructor(
    private val baseUrl:String,
    private val privateApiKey: String,
    private val publicApiKey : String,
    private val characterListEndpoint : String,
    private val characterEndpoint : String
) {
    private val httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                when (statusCode) {
                    in 300..399 -> throw RedirectResponseException(response,"Cached response text")
                    in 400..499 -> throw ClientRequestException(response,"Cached response text")
                    in 500..599 -> throw ServerResponseException(response,"Cached response text")
                }

                if (statusCode >= 600) {
                    throw ResponseException(response,"Cached response text")
                }
            }
        }
        /**
         * Default parameter that are need for all the request
         */
        val timestamp = System.currentTimeMillis().toString()
        defaultRequest {
            parameter(TS, timestamp)
            parameter(APIKEY,publicApiKey)
            parameter(HASH,generateApiHash(timestamp))
        }
    }

    /**
     * Gets a list of marvel characters with an encapsulated response
     * @param limit max number of characters that the api is allowed to provide
     * @param offset the number characters that we want to skip
     * @return The encapsulated response with a list of characters
     */
    suspend fun getMarvelCharacters(
        limit : Int,
        offset : Int
    ) : BaseResponse<CharacterResponse> {
        return httpClient.get(
            scheme = "https" ,
            host = baseUrl,
            port = 443,
            path = characterListEndpoint
        ){
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
        characterId : Long,
    ) : BaseResponse<CharacterResponse> {
        return httpClient.get(
            scheme = "https" ,
            host = baseUrl,
            port = 443,
            path =  "$characterEndpoint/$characterId"
        )
    }

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) : String{
        return (timestamp + privateApiKey + publicApiKey).md5()
    }
}

