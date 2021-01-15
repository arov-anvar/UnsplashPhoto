package com.anvar.unsplashphoto.ui.user

import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewPaginator(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {

    private val threshold = 3
    private val layoutManager: RecyclerView.LayoutManager?

    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE && layoutManager != null && layoutManager is LinearLayoutManager) {

            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            if (firstVisibleItemPosition + threshold >= totalItemCount) {
                loadMore()
            }
        }
    }

    abstract fun loadMore()
}