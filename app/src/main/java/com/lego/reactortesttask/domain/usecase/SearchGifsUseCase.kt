package com.lego.reactortesttask.domain.usecase

import com.lego.reactortesttask.domain.repository.GiphyRepository

class SearchGifsUseCase(private val giphyRepository: GiphyRepository) {

    fun search(query: String, offset: Int) = giphyRepository.search(query, offset)

}