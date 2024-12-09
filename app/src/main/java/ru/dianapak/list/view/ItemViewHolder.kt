package ru.dianapak.list.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.dianapak.list.R

class ItemViewHolder(
    itemView: View
) : ViewHolder(itemView) {

    val item: TextView = itemView.findViewById(R.id.item)
}