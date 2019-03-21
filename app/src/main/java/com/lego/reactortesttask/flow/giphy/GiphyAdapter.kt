package com.lego.reactortesttask.flow.giphy

import android.view.View
import android.view.ViewGroup
import com.lego.reactortesttask.R
import com.lego.reactortesttask.base.BaseListAdapter
import com.lego.reactortesttask.base.BaseViewHolder
import com.lego.reactortesttask.domain.entity.GiphyEntity
import com.lego.reactortesttask.utils.GIF_SIZE
import com.lego.reactortesttask.utils.GIF_URL
import com.lego.reactortesttask.utils.inflate
import com.lego.reactortesttask.utils.setGif
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.vh_gif.*

class GiphyAdapter : BaseListAdapter<GiphyEntity, BaseViewHolder<GiphyEntity>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GiphyEntity> {
        return GiphyViewHolder(parent.inflate(R.layout.vh_gif))
    }

    inner class GiphyViewHolder(override val containerView: View) : BaseViewHolder<GiphyEntity>(containerView),
        LayoutContainer {

        override fun bind(model: GiphyEntity) {
            gifContent.setGif(GIF_URL + model.gif + GIF_SIZE)
        }

    }

}