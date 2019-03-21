package com.lego.reactortesttask.data.repository

import com.lego.reactortesttask.data.mapper.toDomain
import com.lego.reactortesttask.data.remote.GiphyRemoteDataSource
import com.lego.reactortesttask.domain.entity.GiphyEntity
import com.lego.reactortesttask.domain.repository.GiphyRepository
import io.reactivex.Single

class GiphyDataRepository(private val remoteDataSource: GiphyRemoteDataSource) : GiphyRepository {

    override fun search(query: String, offset: Int): Single<List<GiphyEntity>> =
        remoteDataSource.search(query, offset)
            .map { it.toDomain() }

}