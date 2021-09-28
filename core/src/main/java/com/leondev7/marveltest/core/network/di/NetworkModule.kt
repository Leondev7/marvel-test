package com.leondev7.marveltest.core.network.di

import com.leondev7.marveltest.core.network.MarvelApi
import com.leondev7.marveltest.core.network.MarvelHttpClient
import org.koin.dsl.module

/**
 * Network Module for injecting the api client
 */
val networkModule = module {
    single {
        MarvelApi(
            httpClient = MarvelHttpClient(get(),get()).httpClient,
            backgroundDispatcher = get(),
            baseUrl = "gateway.marvel.com",
            characterEndpoint = "/v1/public/characters",
            characterListEndpoint = "/v1/public/characters"
        )
    }
}
