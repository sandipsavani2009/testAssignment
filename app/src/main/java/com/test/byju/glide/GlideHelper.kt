package com.test.byju.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.test.byju.R

/**
 * Kotlin EX for imageview
 */
fun ImageView.setUrl(url: String) {
    Glide.with(context).
        load(url).
        apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.loading)).
        into(this)
}