package com.lego.reactortesttask.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lego.reactortesttask.R

fun ImageView.setGif(path: String?, @DrawableRes placeholder: Int = R.drawable.ic_placeholder) {
    Glide.with(this.context)
        .load(path)
        .apply(
            RequestOptions()
                .error(placeholder)
        )
        .into(this)
}
