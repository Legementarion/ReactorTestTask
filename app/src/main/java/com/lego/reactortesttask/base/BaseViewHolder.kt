package com.lego.reactortesttask.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in M>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: M)
}