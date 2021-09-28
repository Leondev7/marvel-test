package com.leondev7.marveltest.core.usecases

sealed class UseCaseResult {
    class Success<T>(val content : T?) : UseCaseResult()
    class Failure(val error : Throwable) : UseCaseResult()
}