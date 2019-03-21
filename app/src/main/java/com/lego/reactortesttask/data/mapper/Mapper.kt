package com.lego.reactortesttask.data.mapper

import com.lego.reactortesttask.data.remote.models.GiphyResponse
import com.lego.reactortesttask.domain.entity.GiphyEntity

fun GiphyResponse.toDomain(): Pair<List<GiphyEntity>, Int> {
    val list = mutableListOf<GiphyEntity>()
    gifObjects.forEach {
        list.add(GiphyEntity(it.id))
    }
    return Pair(list,this.paginationObject.totalCount)
}
