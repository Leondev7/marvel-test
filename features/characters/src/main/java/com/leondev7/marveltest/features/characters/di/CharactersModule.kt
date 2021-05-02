package com.leondev7.marveltest.features.characters.di

import com.leondev7.marveltest.core.network.MarvelApi
import com.leondev7.marveltest.features.characters.data.CharactersRepository
import com.leondev7.marveltest.features.characters.domain.repository.ICharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for injecting Characters feature dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
class CharactersModule {

    /**
     * Provides a Singleton of [ICharactersRepository]
     * @param marvelApi The Ktor Marvel API to get the results
     */
    @Provides
    @Singleton
    fun provideRepo(marvelApi: MarvelApi) :ICharactersRepository = CharactersRepository(marvelApi)
}