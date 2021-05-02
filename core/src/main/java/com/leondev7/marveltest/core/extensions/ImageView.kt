package com.leondev7.marveltest.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * ImageView extensions for centralized processing
 * Easily interchangeable with Picasso or another image processing library
 */

fun ImageView.getImageByUrlCenterCrop(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun ImageView.getImageByUrl(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}
