package com.lego.reactortesttask.data.remote.models

import com.google.gson.annotations.SerializedName

data class GifObject(
    @SerializedName("id") val id: String,
    @SerializedName("url") val gif: String = ""
)