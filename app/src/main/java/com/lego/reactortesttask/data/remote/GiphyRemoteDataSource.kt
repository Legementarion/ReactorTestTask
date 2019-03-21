package com.lego.reactortesttask.data.remote

import com.lego.reactortesttask.data.remote.models.GiphyResponse
import io.reactivex.Single

interface GiphyRemoteDataSource {

    fun search(query: String, offset: Int): Single<GiphyResponse>

}