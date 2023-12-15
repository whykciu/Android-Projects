package com.example.project6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project6.modules.BagEntity
import com.example.project6.modules.Category
import com.example.project6.modules.Product
import com.example.project6.modules.ShoppingBag
import com.example.project6.modules.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf

class MainActivity : AppCompatActivity() {

    private lateinit var categories: List<Category>
    private lateinit var products: List<Product>
    private lateinit var users: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        val config = RealmConfiguration.Builder(
            schema = setOf(User::class, ShoppingBag::class, BagEntity::class, Product::class, Category::class)
        )
            .name("shopDatabaseRealm.realm")
            .initialData{
                for(u in users){
                    copyToRealm(u)
                }
            }
            .build()

        val realm = Realm.open(config)

        val result = realm.query<User>().find()

        val adapter = ListAdapter(result)
        val recyclerView: RecyclerView = findViewById(R.id.rvUsers)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initData(){

        categories = listOf(
            Category("Electronics"),
            Category("Fashion"),
            Category("Home"),
            Category("Sport")
        )

        products = listOf(
            Product("Laptop", 2000.0, categories[0]),
            Product("PC", 4000.0, categories[0]),
            Product("T-shirt", 100.0, categories[1]),
            Product("Shoes", 300.0, categories[1]),
            Product("Sofa", 1000.0, categories[2]),
            Product("Bed", 1000.0, categories[2]),
            Product("Ball", 100.0, categories[3]),
            Product("Bike", 1500.0, categories[3])
        )

        users = listOf(
            User("Maciej", "Kowalski", "goodnickname1", realmListOf(
                BagEntity(products[0], 1), BagEntity(products[2], 3)
            )),
            User("Jan", "Budzik", "normalUserPL", realmListOf(
                BagEntity(products[4], 2), BagEntity(products[6], 5), BagEntity(products[7], 1)
            ))
        )

    }

}