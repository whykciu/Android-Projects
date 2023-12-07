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
        val ivDesc: ImageView = findViewById(R.id.ivDescImage)
        val backBtn: ImageView = findViewById(R.id.iBtnBack)

        val name = intent.getStringExtra("NAME")
        val description = intent.getStringExtra("DESCRIPTION")
        val image = intent.getStringExtra("IMAGE_ID")

        tvName.text = name
        tvDesc.text = description
        ivDesc.setImageResource(image!!.toInt())

        backBtn.setOnClickListener{
            finish()
        }

    }
}