package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import java.util.Date

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

            Item("pierwsze zadanie", "opis1", Date(), false),
            Item("drugie zadanie", "opis2", Date(), false),
            Item("trzecie zadanie", "opis3", Date(), false),
            Item("czwarte zadanie", "opis4", Date(), false),
            Item("piate zadanie", "opis5", Date(), true),
            Item("szoste zadanie", "opis6", Date(), false),
            Item("siodme zadanie", "opis7", Date(), false),
            Item("osme zadanie", "opis8", Date(), false),
            Item("dziewiate zadanie", "opis9", Date(), false),
            Item("trudne zadanie", "opis10", Date(), true),
            Item("latwe zadanie", "opis11", Date(), false),
            Item("umiarkowane zadanie", "opis12", Date(), false),
            Item("dobre zadanie", "opis13", Date(), false),
            Item("ostatnie zadanie", "opis14", Date(), false)

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

