package co.utils.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var listItems: List<T>

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor() {
        listItems = emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
        return getViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position], position)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(data: T, pos: Int)
    }
}