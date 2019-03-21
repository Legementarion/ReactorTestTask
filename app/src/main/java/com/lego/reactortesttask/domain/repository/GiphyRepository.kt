package com.lego.reactortesttask.domain.repository

import com.lego.reactortesttask.domain.entity.GiphyEntity
import io.reactivex.Single

interface GiphyRepository {

    fun search(query: String, offset: Int): Single<Pair<List<GiphyEntity>, Int>>

}