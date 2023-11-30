package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var toDoAdapter : ToDoAdapter
    lateinit var items : MutableList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = InitItemsList()
        toDoAdapter = ToDoAdapter(items)

        val recyclerView: RecyclerView = findViewById(R.id.rvList)
        recyclerView.adapter = toDoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerView)

    }

    fun InitItemsList() : MutableList<Item>{

        return mutableListOf(

            Item("pierwsze zadanie"),
            Item("drugie zadanie"),
            Item("trzecie zadanie"),
            Item("czwarte zadanie"),
            Item("piate zadanie"),
            Item("szoste zadanie"),
            Item("siodme zadanie"),
            Item("osme zadanie"),
            Item("dziewiate zadanie"),
            Item("trudne zadanie"),
            Item("latwe zadanie"),
            Item("umiarkowane zadanie"),
            Item("dobre zadanie"),
            Item("ostatnie zadanie")

        )

    }

    val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            val snackbar = Snackbar.make(findViewById(R.id.mainLayout), "Item deleted", Snackbar.LENGTH_LONG)
            snackbar.show()

            items.removeAt(viewHolder.adapterPosition)
            toDoAdapter.notifyDataSetChanged()

        }

    }

}

