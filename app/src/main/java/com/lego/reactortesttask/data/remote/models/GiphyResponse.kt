package com.lego.reactortesttask.data.remote.models

import com.google.gson.annotations.SerializedName

data class GiphyResponse (
    @SerializedName("data") val gifObjects: List<GifObject>,
    @SerializedName("pagination") val paginationObject: PaginationObject,
    @SerializedName("meta") val metaData: MetaData
)