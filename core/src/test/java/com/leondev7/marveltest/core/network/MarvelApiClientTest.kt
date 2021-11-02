package com.leondev7.marveltest.core.network

import com.leondev7.marveltest.core.network.mock.MockResponse
import com.leondev7.marveltest.core.network.mock.mockClient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals

class MarvelApiClientTest : KoinTest {

    val apiClient: MarvelApiClient by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single {
                    MarvelApiClient(
                        retryPolicy = DefaultRetryPolicy(),
                        httpClient = mockClient,
                        backgroundDispatcher = TestCoroutineDispatcher(),
                        baseUrl = Endpoints.baseUrl,
                        characterEndpoint = Endpoints.characterEndpoint,
                        characterListEndpoint = Endpoints.characterListEndpoint
                    )
                }
            })
    }

    @Test
    fun `get characters list return 200`() = runBlocking {
        apiClient.getMarvelCharacters(0,0).collect { response->
            assertEquals(MockResponse.characterResponse, response)
        }

    }

    @Test
    fun `get single character return 200`() = runBlocking {
        apiClient.getCharacterDetail(0).collect { response->
            assertEquals(MockResponse.characterResponse, response)
        }

    }


}
