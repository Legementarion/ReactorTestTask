package com.lego.reactortesttask.data.gateaway

import com.lego.reactortesttask.data.remote.models.GiphyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET(GET_GIFS_PATH)
    fun getGifs(@Query("q") query: String, @Query("offset") offset: Int): Single<GiphyResponse>

    companion object {
        const val GET_GIFS_PATH = "/v1/gifs/search"
    }

}