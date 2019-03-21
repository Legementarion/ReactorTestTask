package com.lego.reactortesttask.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.lego.reactortesttask.R

fun ImageView.setGif(path: String?, @DrawableRes placeholder: Int = R.drawable.ic_placeholder) {
    Glide.with(this.context)
        .asGif()
        .load(path)
        .apply(
            RequestOptions()
                .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(this)
}
