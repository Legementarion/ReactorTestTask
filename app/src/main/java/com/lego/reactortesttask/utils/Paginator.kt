package com.lego.reactortesttask.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Paginator(recyclerView: RecyclerView, onScrolled: () -> Unit) {

    private var isLoading = false
    private var isLastPage = false
    var focus = RecyclerView.FOCUS_DOWN

    init {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading && !isLastPage) {

                    recyclerView.let {
                        if (focus == RecyclerView.FOCUS_DOWN) {
                            if (dy > 0 && !recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                                isLoading = true
                                onScrolled()
                            }
                        } else {
                            if (dy < 0) {
                                with(recyclerView.layoutManager as LinearLayoutManager) {
                                    val visibleItemCount = childCount
                                    val totalItemCount = itemCount
                                    val pastVisiblesItems = findFirstVisibleItemPosition()

                                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                        isLoading = true
                                        onScrolled()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    fun complete() {
        isLastPage = true
    }

    fun loaded() {
        isLoading = false
    }

}