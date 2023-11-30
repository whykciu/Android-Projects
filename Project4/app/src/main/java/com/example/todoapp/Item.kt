package com.example.todoapp

import java.util.Date

data class Item(
    val text : String,
    val desc: String,
    val date: Date,
    val status: Boolean
)
