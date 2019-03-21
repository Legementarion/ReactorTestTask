package com.lego.reactortesttask.data.remote.models

import com.google.gson.annotations.SerializedName

class PaginationObject (
    @SerializedName("offset") val offset: Int,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("count") val count: Int
)