package com.leondev7.marveltest.core.network.mock

import com.leondev7.marveltest.core.network.Endpoints
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString

val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

val mockClient = HttpClient(MockEngine) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    engine {
        addHandler { request ->
            when (request.url.encodedPath) {
                Endpoints.characterListEndpoint -> {
                    respond(Json.encodeToString(MockResponse.characterResponse), HttpStatusCode.OK, responseHeaders)
                }
                String.format(Endpoints.characterEndpoint,0) -> {
                    respond(Json.encodeToString(MockResponse.characterResponse), HttpStatusCode.OK, responseHeaders)
                }
                else -> {
                    error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
    }
}