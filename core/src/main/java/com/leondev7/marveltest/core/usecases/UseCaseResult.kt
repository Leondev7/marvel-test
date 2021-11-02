package com.leondev7.marveltest.core.usecases

sealed class UseCaseResult<out T : Any> {
    data class Success<out T : Any>(val data : T) : UseCaseResult<T>()
    data class Failure(val exception: Throwable) : UseCaseResult<Nothing>()
}