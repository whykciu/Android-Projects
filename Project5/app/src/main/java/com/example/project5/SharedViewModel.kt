package com.example.project5

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.AbstractMap.SimpleEntry

class SharedViewModel: ViewModel() {

    private val drawable = R.drawable.burger

    private var products: MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf(
         Product("Burger", "A savory handheld delight featuring a grilled or fried patty, nestled in a bun with various toppings and condiments.", R.drawable.burger, 0, 15),
         Product("Pizza", "A circular, oven-baked flatbread adorned with tomato sauce, cheese, and a variety of toppings, delivering a delicious medley of flavors.",  R.drawable.pizza, 0, 15),
         Product("Lasagne", " Layers of pasta, rich meat sauce, creamy béchamel, and melted cheese harmoniously baked to create a hearty Italian casserole.",  R.drawable.lasagne, 0, 15),
         Product("Salad", " A crisp and refreshing dish comprised of fresh vegetables, greens, and often accompanied by proteins, dressed to perfection for a healthy and satisfying meal.",  R.drawable.salad, 0, 15),
         Product("French Fries", "Deep-fried strips of potatoes, seasoned to golden perfection, offering a crispy and flavorful side dish.\n",  R.drawable.fries, 0, 15),
         Product("Sandwich", "A customizable culinary creation featuring layers of ingredients such as meats, cheeses, vegetables, and condiments, enclosed between slices of bread.",  R.drawable.sandwich, 0, 15),
         Product("Shake", "A luscious and indulgent beverage blending ice cream, milk, and various flavors, creating a creamy and satisfying treat.",  R.drawable.shake, 0, 15),
         Product("Kebab", "Grilled or skewered pieces of marinated meat, often accompanied by vegetables, delivering a flavorful and aromatic dish.",  R.drawable.kebab, 0, 15),
         Product("Spaghetti", " Long, thin pasta noodles typically served with a variety of sauces, from classic marinara to creamy Alfredo, providing a versatile and beloved Italian staple.",  R.drawable.spaghetti, 0, 15)
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

    fun increaseQuantityInBag(position: Int){
        val product = bagProducts.value!![position].key
        val index = products.value!!.indexOf(product)

        if(products.value!![index].stockLevel > 0){
            bagProducts.value!![position].setValue(bagProducts.value!![position].value + 1)
            products.value!![index].stockLevel--
        }

        products.value = products.value
        bagProducts.value = bagProducts.value
    }

    fun decreaseQuantity(position: Int){
        val product = products.value?.get(position)
        if(product!!.quantity - 1 >= 0){
            product.quantity--
        }
        products.value = products.value
    }

    fun decreaseQuantityInBag(position: Int){
        val product = bagProducts.value!![position].key
        val index = products.value!!.indexOf(product)

        bagProducts.value!![position].setValue(bagProducts.value!![position].value - 1)
        products.value!![index].stockLevel++
        products.value = products.value
        bagProducts.value = bagProducts.value

    }

    fun deleteFromBag(position: Int){
        val product = bagProducts.value!![position].key
        val index = products.value!!.indexOf(product)
        products.value!![index].stockLevel += bagProducts.value!![position].value
        bagProducts.value!!.removeAt(position)

        products.value = products.value
    }

    fun addToBag(position: Int){
        var product = products.value?.get(position)
        product!!.stockLevel -= product!!.quantity

        var i = 0
        for(entry in bagProducts.value!!){
            if(product == entry.key){
                val currentQuantity = bagProducts.value!!.get(i).value
                bagProducts.value!![i].setValue(currentQuantity + product.quantity)
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