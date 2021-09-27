package com.leondev7.marveltest.core.network.error

/**
 * Types of Network related failures
 */
sealed class NetworkFailure : Throwable() {
    class Redirect(val code: Int, override val message: String) : NetworkFailure()
    class ClientError(val code: Int, override val message: String) : NetworkFailure()
    class ServerError(val code: Int, override val message: String) : NetworkFailure()
    class UnknownError(override val message: String) : NetworkFailure()
}