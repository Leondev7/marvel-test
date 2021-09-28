package com.leondev7.marveltest.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

/**
 * Core module exposing Background Dispatcher
 */
val coreModule = module {
    single<CoroutineContext> { Dispatchers.IO }
}
