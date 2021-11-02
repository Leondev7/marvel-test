package com.leondev7.marveltest.core.network

interface RetryPolicy {
    val numRetries: Long
    val delayMillis: Long
    val delayFactor: Long
}

data class DefaultRetryPolicy(
    override val numRetries: Long = 3,
    override val delayMillis: Long = 2000,
    override val delayFactor: Long = 2
) : RetryPolicy