package com.leondev7.marveltest.core.network.di

import com.leondev7.marveltest.core.network.*
import org.koin.dsl.module

/**
 * Network Module for injecting the api client
 */
val networkModule = module {
    single<IMarvelApiClient> {
        MarvelApiClient(
            retryPolicy = DefaultRetryPolicy(),
            httpClient = MarvelHttpClient(get(),get()).httpClient,
            backgroundDispatcher = get(),
            baseUrl = Endpoints.baseUrl,
            characterEndpoint = Endpoints.characterEndpoint,
            characterListEndpoint = Endpoints.characterListEndpoint
        )
    }
}
