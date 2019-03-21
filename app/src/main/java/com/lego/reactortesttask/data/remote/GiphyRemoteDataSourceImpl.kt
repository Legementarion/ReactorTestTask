package com.lego.reactortesttask.data.remote

import com.lego.reactortesttask.data.gateaway.GiphyApi

class GiphyRemoteDataSourceImpl(private val giphyApi: GiphyApi): GiphyRemoteDataSource {

    override fun search(query: String, offset: Int) =
        giphyApi.getGifs(query, offset)

}