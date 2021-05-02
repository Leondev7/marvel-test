package com.leondev7.marveltest.core.extensions

import java.math.BigInteger
import java.security.MessageDigest

/**
 *  Convert any string to Message-Digest Algorithm 5 (MD5)
 *
 *  @return MD5 string.
 */

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}