package com.lego.reactortesttask.flow.giphy

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.lego.reactortesttask.base.BaseViewModel
import com.lego.reactortesttask.domain.usecase.SearchGifsUseCase
import com.lego.reactortesttask.utils.Paginator
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
    var errorDelegate: ((error: String) -> Unit)? = null
    private var currentQuery: String? = null
    private var offset: Int = 0
    private var totalCount: Int = 0

    fun search(query: String, paginator: Paginator?) {
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
                    paginator?.loaded()
                    isEmptyState.set(it.first.isEmpty())
                    adapter.get()?.setItems(it.first)
                    offset += it.first.size
                    totalCount = it.second
                },
                onError = {
                    isLoading.set(false)
                    paginator?.loaded()
                    errorDelegate?.invoke(it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    fun uploadMore(paginator: Paginator?) {
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
                        paginator?.loaded()
                        adapter.get()?.addItems(it.first)
                        offset += it.first.size
                        if (offset >= totalCount) {
                            paginator?.complete()
                        }
                    },
                    onError = {
                        isLoading.set(false)
                        paginator?.loaded()
                        errorDelegate?.invoke(it.localizedMessage)
                    }
                ).addTo(compositeDisposable)
        }
    }

}
