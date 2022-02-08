package com.example.taskmanagement

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults

class CustomRecyclerViewAdapter(realmResults: RealmResults<Todo>):RecyclerView.Adapter<ViewHolder> (){

    private val rResults:RealmResults<Todo> = realmResults

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_result, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoData = rResults[position]
        holder.todoText?.text = todoData?.todoText.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, EditActivity::class.java)
            intent.putExtra("id", todoData?.id)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return rResults.size
    }

}