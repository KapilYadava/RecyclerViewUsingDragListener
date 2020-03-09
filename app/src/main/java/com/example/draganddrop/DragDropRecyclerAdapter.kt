package com.example.draganddrop

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recyclerview_item.view.*

class DragDropRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<DragDropRecyclerAdapter.ItemViewHolder>(){

    private var users = emptyList<String>().toMutableList()

    fun setUsers(newUsers: List<String>) {
        users.addAll(newUsers)
    }

    fun getUsers(): List<String> {
        return users
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.itemView.tag = position
        holder.itemView.setOnLongClickListener{ v: View ->
            val dragData = ClipData.newPlainText("", "")
            val myShadow = View.DragShadowBuilder(v)
            v.startDragAndDrop(
                dragData,   // the data to be dragged
                myShadow,   // the drag shadow builder
                v,       // no need to use local data
                0           // flags (not currently used, set to 0)
            )
            return@setOnLongClickListener true

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recyclerview_item, parent, false)
        return ItemViewHolder(itemView)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.textView.text = text
        }
    }
}