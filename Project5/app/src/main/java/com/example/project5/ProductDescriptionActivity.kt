package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProductDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        val tvName: TextView = findViewById(R.id.tvName)
        val tvDesc: TextView = findViewById(R.id.tvDescription)
        val backBtn: ImageView = findViewById(R.id.iBtnBack)

        val name = intent.getStringExtra("NAME")
        val description = intent.getStringExtra("DESCRIPTION")

        tvName.text = name
        tvDesc.text = description

        backBtn.setOnClickListener{
            finish()
        }

    }
}