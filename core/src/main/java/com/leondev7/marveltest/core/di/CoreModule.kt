package com.leondev7.marveltest.core.di

import com.leondev7.marveltest.core.BuildConfig
import com.leondev7.marveltest.core.network.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
private const val API_PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
private const val BASE_URL = BuildConfig.MARVEL_API_BASE_URL
private const val LIST_ENDPOINT = BuildConfig.CHARACTER_LIST_ENDPOINT
private const val CHARACTER_ENDPOINT = BuildConfig.CHARACTER_ENDPOINT

/**
 * Core module providing dependencies for the whole application
 */
@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    /**
     * Provides the API client needed for making http requests
     */
    @Singleton
    @Provides
    fun provideMarvelApi() = MarvelApi(BASE_URL, API_PRIVATE_KEY, API_PUBLIC_KEY, LIST_ENDPOINT,
        CHARACTER_ENDPOINT)
}
