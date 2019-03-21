package com.lego.reactortesttask.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<M, VH : BaseViewHolder<M>> : RecyclerView.Adapter<VH>() {

    protected open val items: MutableList<M> = mutableListOf()

    protected open val itemsTheSameComparator: ((oldItem: M, newItem: M) -> Boolean) = { oldItem, newItem ->
        oldItem == newItem
    }

    protected open val contentTheSameComparator: ((oldItem: M, newItem: M) -> Boolean) = { oldItem, newItem ->
        itemsTheSameComparator.invoke(oldItem, newItem)
    }

    open fun addItems(items: List<M>) {
        val newItems = ArrayList(this.items).apply { addAll(items) }
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallBack(oldList = this.items, newList = newItems))
        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    open fun setItems(items: List<M>) {
        val newItems = ArrayList(items)
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallBack(oldList = this.items, newList = newItems))
        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItems(vararg items: M) {
        addItems(items.toList())
    }

    fun remove(model: M) {
        items.indexOf(model).apply {
            if (this != -1) {
                items.removeAt(this)
                notifyItemRemoved(this)
            }
        }
    }

    open fun getAll() = items.toList()

    open fun clear() {
        val count = items.count()
        items.clear()
        notifyItemRangeRemoved(0, count)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: VH, pposition: Int) {
        viewHolder.bind(items[pposition])
    }

    protected inner class DiffUtilCallBack(val oldList: List<M>, val newList: List<M>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(firstPos: Int, secondPos: Int) = itemsTheSameComparator(oldList[firstPos], newList[secondPos])

        override fun areContentsTheSame(firstPos: Int, secondPos: Int) = contentTheSameComparator(oldList[firstPos], newList[secondPos])
    }
}
