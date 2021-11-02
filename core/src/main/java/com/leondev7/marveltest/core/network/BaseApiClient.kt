package com.leondev7.marveltest.core.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import kotlin.coroutines.CoroutineContext

abstract class BaseApiClient {

    protected suspend inline fun <T> safeApiCall(
        backgroundDispatcher: CoroutineContext,
        retryPolicy: RetryPolicy,
        apiCall: () -> T
    ): Flow<T> {
        return flowOf(apiCall()).flowOn(
            backgroundDispatcher
        ).retryWhen { cause, attempt ->
            if (cause is IOException && attempt < retryPolicy.numRetries) {
                delay( retryPolicy.delayMillis *attempt * retryPolicy.delayFactor)
                return@retryWhen true
            } else {
                return@retryWhen false
            }
        }

    }
}