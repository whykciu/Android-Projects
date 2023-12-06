package com.example.project5

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.AbstractMap.SimpleEntry

class SharedViewModel: ViewModel() {

    private val drawable: Drawable = R.drawable.burger.toDrawable()

    private var products: MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf(
         Product("Burger", "opis1", drawable, 0, 15),
         Product("Pizza", "opis2",  drawable, 0, 15),
         Product("Lasagna", "opis3",  drawable, 0, 15),
         Product("Salad", "opis4",  drawable, 0, 15),
         Product("French Fries", "opis5",  drawable, 0, 15),
         Product("Sandwich", "opis6",  drawable, 0, 15),
         Product("Shake", "opis7",  drawable, 0, 15),
         Product("Kebab", "opis8",  drawable, 0, 15),
         Product("Spaghetti", "opis9",  drawable, 0, 15)
    ))

    private var bagProducts: MutableLiveData<MutableList<MutableMap.MutableEntry<Product, Int>>> = MutableLiveData(ArrayList())

    fun getAllProducts(): LiveData<MutableList<Product>> {
        return products
    }

    fun getBagProducts(): LiveData<MutableList<MutableMap.MutableEntry<Product, Int>>> {
        return bagProducts
    }

    fun increaseQuantity(position: Int){
        val product = products.value?.get(position)
        if(product!!.quantity + 1 <= product!!.stockLevel){
            product.quantity++
        }
        products.value = products.value
    }

    fun decreaseQuantity(position: Int){
        val product = products.value?.get(position)
        if(product!!.quantity - 1 >= 0){
            product.quantity--
        }
        products.value = products.value
    }

    fun addToBag(position: Int){
        var product = products.value?.get(position)
        product!!.stockLevel -= product!!.quantity

        var i = 0
        for(entry in bagProducts.value!!){
            if(product == entry.key){
                val currentQuantity = bagProducts.value!!.get(i).value
                bagProducts.value!!.get(i).setValue(currentQuantity + product.quantity)
                i = -1
                break
            }
            i++
        }

        if(i != -1){
            bagProducts.value!!.add(SimpleEntry(product, product.quantity))
        }

        product!!.quantity = 0

        products.value = products.value
        bagProducts.value = bagProducts.value
    }

}