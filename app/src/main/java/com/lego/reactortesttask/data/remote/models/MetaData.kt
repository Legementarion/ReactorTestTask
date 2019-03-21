package com.lego.reactortesttask.data.remote.models

import com.google.gson.annotations.SerializedName

class MetaData (
    @SerializedName("msg") val msg: String,
    @SerializedName("status") val status: Int
)