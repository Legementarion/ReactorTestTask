package com.lego.reactortesttask.flow.giphy

import android.app.Application
import android.util.Log
import com.lego.reactortesttask.base.BaseViewModel
import com.lego.reactortesttask.domain.usecase.SearchGifsUseCase
import com.lego.reactortesttask.utils.defaultSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class GiphyViewModel(application: Application, private val searchGifsUseCase: SearchGifsUseCase) :
    BaseViewModel(application) {

    var currentQuery: String? = null
    var offset: Int = 0

    fun search(query: String) {
        currentQuery = query
        searchGifsUseCase
            .search(query, offset)
            .defaultSchedulers()
            .doOnSubscribe {
            }
            .subscribeBy(
                onSuccess = {
                    offset += it.size
                },
                onError = {
                    Log.e(it.javaClass.name, it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    fun uploadMore() {
        currentQuery?.let { query ->
            searchGifsUseCase
                .search(query, offset)
                .defaultSchedulers()
                .doOnSubscribe {
                }
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {
                        Log.e(it.javaClass.name, it.localizedMessage)
                    }
                ).addTo(compositeDisposable)
        }
    }

}
