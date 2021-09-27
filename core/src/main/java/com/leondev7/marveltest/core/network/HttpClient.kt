package com.leondev7.marveltest.core.network

import com.leondev7.marveltest.core.extensions.md5
import com.leondev7.marveltest.core.network.error.NetworkFailure
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*

/**
 * HttpClient
 * @param privateApiKey The marvel private api key
 * @param publicApiKey The marvel public api key
 */
class HttpClient
constructor(
    private val privateApiKey: String,
    private val publicApiKey: String
) {
    val httpClient = HttpClient(CIO) {

        install(WebSockets) {

        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }

        expectSuccess = false


        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                when (statusCode) {
                    in 300..399 -> throw NetworkFailure.Redirect(
                        response.status.value,
                        "Gateway error"
                    )
                    in 400..499 -> throw NetworkFailure.ClientError(
                        response.status.value,
                        "Client Error"
                    )
                    in 500..599 -> throw NetworkFailure.ServerError(
                        response.status.value,
                        "Server Error"
                    )
                }

                if (statusCode >= 600) {
                    throw NetworkFailure.UnknownError("Unknown Network error")
                }
            }
        }

        /**
         * Default parameter that are need for all the request
         */
        val timestamp = System.currentTimeMillis().toString()
        defaultRequest {
            parameter(TS, timestamp)
            parameter(APIKEY, publicApiKey)
            parameter(HASH, generateApiHash(timestamp))
        }

    }

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String): String {
        return (timestamp + privateApiKey + publicApiKey).md5()
    }
}

