package com.lego.reactortesttask.flow.giphy

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.lego.reactortesttask.base.BaseViewModel
import com.lego.reactortesttask.domain.usecase.SearchGifsUseCase
import com.lego.reactortesttask.utils.defaultSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class GiphyViewModel(application: Application, private val searchGifsUseCase: SearchGifsUseCase) :
    BaseViewModel(application) {

    val adapter = ObservableField<GiphyAdapter>().apply {
        set(GiphyAdapter())
    }
    val isLoading = ObservableBoolean().apply {
        set(false)
    }
    val isEmptyState = ObservableBoolean()
    var currentQuery: String? = null
    var offset: Int = 0

    fun search(query: String) {
        offset = 0
        currentQuery = query
        searchGifsUseCase
            .search(query, offset)
            .defaultSchedulers()
            .doOnSubscribe {
                isLoading.set(true)
            }
            .subscribeBy(
                onSuccess = {
                    isLoading.set(false)
                    isEmptyState.set(it.isEmpty())
                    adapter.get()?.setItems(it)
                    offset += it.size
                },
                onError = {
                    isLoading.set(false)
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
                    isLoading.set(true)
                }
                .subscribeBy(
                    onSuccess = {
                        isLoading.set(false)
                        adapter.get()?.addItems(it)
                        offset += it.size
                    },
                    onError = {
                        isLoading.set(false)
                        Log.e(it.javaClass.name, it.localizedMessage)
                    }
                ).addTo(compositeDisposable)
        }
    }

}
