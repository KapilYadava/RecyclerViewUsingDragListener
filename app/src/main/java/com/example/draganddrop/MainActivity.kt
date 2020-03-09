package com.example.draganddrop

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.DragStartHelper
import androidx.core.view.DragStartHelper.OnDragStartListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnDragListener{

    private lateinit var adapter: DragDropRecyclerAdapter
    private lateinit var adapter2: DragDropRecyclerAdapter
    private lateinit var touchHelper: ItemTouchHelper
    private lateinit var touchHelper2:ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DragDropRecyclerAdapter(this)
        adapter2 = DragDropRecyclerAdapter(this)
        populateListItem()
        recyclerView.adapter = adapter
        recyclerView2.adapter = adapter2

        recyclerView.setOnDragListener(this)
        recyclerView2.setOnDragListener(this)
    }

    private fun populateListItem() {
        val users = listOf(
            "Anuj",
            "Bhanu",
            "Chetan",
            "Devendra",
            "Esha",
            "Farmod",
            "Ganesh",
            "Hemant",
            "Ishaan",
            "Jack",
            "Kamal",
            "Lalit",
            "Mona"
        )
        adapter.setUsers(users)


        val users2 = listOf(
            "Photo",
            "Video",
            "Pro",
            "Panorama",
            "Slow Mothion",
            "More"
        )
        adapter2.setUsers(users2)

    }

    override fun onDrag(v: View, event: DragEvent): Boolean {
        return setDrag(v, event)
    }

    private fun setDrag(v: View, event: DragEvent): Boolean{
        when (event.action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                Log.v("Kapil", "Started")
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                v.setBackgroundColor(Color.GREEN)
                Log.v("Kapil", "Entered!")

            }
            DragEvent.ACTION_DRAG_EXITED -> {
                v.setBackgroundColor(Color.BLUE)
            }
            DragEvent.ACTION_DROP ->{

                    val viewSource = event.localState as View
                    val recyclerViewSource = viewSource.parent as RecyclerView
                    val adapterSource = recyclerViewSource.adapter as DragDropRecyclerAdapter
                    val positionSource = viewSource.tag as Int
                    val listSource = adapterSource.getUsers() as MutableList
                    val dragItem = listSource[positionSource]
                    listSource.removeAt(positionSource)
                    adapterSource.notifyItemRemoved(positionSource)
                    adapterSource.notifyItemRangeChanged(positionSource, listSource.size)


                    val recyclerViewTarget = v as RecyclerView
                    val adapterTarget = recyclerViewTarget.adapter as DragDropRecyclerAdapter
                    val listTarget = adapterTarget.getUsers() as MutableList
                    //val positionTarget = v.tag as Int
                    listTarget.add(dragItem)
                    adapterTarget.notifyDataSetChanged()

                v.setBackgroundColor(Color.TRANSPARENT)

            }
            DragEvent.ACTION_DRAG_LOCATION ->{
                //true

            }
            DragEvent.ACTION_DRAG_ENDED ->{

                //v.setBackgroundColor(Color.TRANSPARENT)
                when(event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)
                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()
            }
            else -> {

            }
        }

        return true
    }

    private fun setDrag2(v: View, event: DragEvent): Boolean{
        when (event.action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                Log.v("Kapil", "Started")
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                v.setBackgroundColor(Color.RED)
                Log.v("Kapil", "Entered!")

            }
            DragEvent.ACTION_DRAG_EXITED -> {
                v.setBackgroundColor(Color.BLUE)

            }
            DragEvent.ACTION_DROP ->{

                val viewSource = event.localState as View
                val recyclerViewSource = viewSource.parent as RecyclerView
                val adapterSource = recyclerViewSource.adapter as DragDropRecyclerAdapter
                val positionSource = viewSource.tag as Int
                val listSource = adapterSource.getUsers() as MutableList
                val dragItem = listSource[positionSource]
                listSource.removeAt(positionSource)
                adapterSource.notifyItemRemoved(positionSource)
                adapterSource.notifyItemRangeChanged(positionSource, listSource.size)


                val recyclerViewTarget = v as RecyclerView
                val adapterTarget = recyclerViewTarget.adapter as DragDropRecyclerAdapter
                val listTarget = adapterTarget.getUsers() as MutableList
                //val positionTarget = v.tag as Int
                listTarget.add(dragItem)
                adapterTarget.notifyDataSetChanged()

                v.setBackgroundColor(Color.TRANSPARENT)

            }
            DragEvent.ACTION_DRAG_LOCATION ->{

            }
            DragEvent.ACTION_DRAG_ENDED ->{

                v.setBackgroundColor(Color.TRANSPARENT)
                when(event.result) {
                    true ->
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG)
                    else ->
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG)
                }.show()

            }
            else -> {

            }
        }

        return true
    }

}
