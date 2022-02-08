package com.example.taskmanagement

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView) {
    var todoText: TextView? = null

    init {
        todoText = itemView.findViewById(R.id.textview)
    }
}
