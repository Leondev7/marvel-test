package com.leondev7.marveltest.core.extensions

import android.widget.ImageView
import coil.load

/**
 * ImageView extensions for centralized processing
 * Easily interchangeable with Picasso or another image processing library
 */

fun ImageView.getImageByUrlCenterCrop(url: String) {
    this.load(url)
}

fun ImageView.getImageByUrl(url: String?) {
    if (url != null) {
        this.load(url)
    }
}
