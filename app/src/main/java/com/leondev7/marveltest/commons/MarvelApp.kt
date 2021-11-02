package com.leondev7.marveltest.commons

import android.app.Application
import com.leondev7.marveltest.core.di.coreModule
import com.leondev7.marveltest.core.network.di.networkModule
import com.leondev7.marveltest.features.characters.di.charactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application class for DI injection purposes
 */
class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApp)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    charactersModule
                )
            )
        }
    }
}