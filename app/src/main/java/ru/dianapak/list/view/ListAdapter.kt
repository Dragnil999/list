package ru.dianapak.list.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dianapak.list.R
import ru.dianapak.list.core.list.SingleLinkedList

@SuppressLint("NotifyDataSetChanged")
class ListAdapter<T : Comparable<T>>(
    private val dataSet: SingleLinkedList<T>,
): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_holder, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.item.text = dataSet.get(position).toString()
    }

    override fun getItemCount(): Int =
        dataSet.size
}