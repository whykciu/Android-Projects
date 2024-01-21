package com.example.project5.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project5.R
import java.math.BigDecimal
import java.util.AbstractMap.SimpleEntry

class SharedViewModel: ViewModel() {

    private val drawable = R.drawable.burger

    private var products: MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf(
         Product("Burger", "A savory handheld delight featuring a grilled or fried patty, nestled in a bun with various toppings and condiments.", R.drawable.burger, 0, BigDecimal(33.49), 15),
         Product("Pizza", "A circular, oven-baked flatbread adorned with tomato sauce, cheese, and a variety of toppings, delivering a delicious medley of flavors.",  R.drawable.pizza, 0, BigDecimal(29.85), 15),
         Product("Lasagne", " Layers of pasta, rich meat sauce, creamy béchamel, and melted cheese harmoniously baked to create a hearty Italian casserole.",  R.drawable.lasagne, 0, BigDecimal(21.50), 15),
         Product("Salad", " A crisp and refreshing dish comprised of fresh vegetables, greens, and often accompanied by proteins, dressed to perfection for a healthy and satisfying meal.",  R.drawable.salad, 0, BigDecimal(16.99), 15),
         Product("French Fries", "Deep-fried strips of potatoes, seasoned to golden perfection, offering a crispy and flavorful side dish.\n",  R.drawable.fries, 0, BigDecimal(10), 15),
         Product("Sandwich", "A customizable culinary creation featuring layers of ingredients such as meats, cheeses, vegetables, and condiments, enclosed between slices of bread.",  R.drawable.sandwich, 0, BigDecimal(7.86),  15),
         Product("Shake", "A luscious and indulgent beverage blending ice cream, milk, and various flavors, creating a creamy and satisfying treat.",  R.drawable.shake, 0, BigDecimal(11), 15),
         Product("Kebab", "Grilled or skewered pieces of marinated meat, often accompanied by vegetables, delivering a flavorful and aromatic dish.",  R.drawable.kebab, 0, BigDecimal(17), 15),
         Product("Spaghetti", " Long, thin pasta noodles typically served with a variety of sauces, from classic marinara to creamy Alfredo, providing a versatile and beloved Italian staple.",  R.drawable.spaghetti, 0, BigDecimal(19.43), 15)
    ))

    private var bagProducts: MutableLiveData<MutableList<MutableMap.MutableEntry<Product, Int>>> = MutableLiveData(ArrayList())
    private var total: MutableLiveData<BigDecimal> = MutableLiveData(BigDecimal(0))
    private var payments: MutableLiveData<MutableList<PaymentModel>> = MutableLiveData(ArrayList())

    fun getAllProducts(): LiveData<MutableList<Product>> {
        return products
    }

    fun getBagProducts(): LiveData<MutableList<MutableMap.MutableEntry<Product, Int>>> {
        return bagProducts
    }

    fun getTotal(): LiveData<BigDecimal> {
        return total
    }

    fun getPayments(): LiveData<MutableList<PaymentModel>> {
        return payments
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
            total.value = total.value!!.add(product.price)
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

        val currentQuantity = bagProducts.value!![position].value

        total.value = total.value!!.subtract(product.price)

        if (currentQuantity > 1) {
            bagProducts.value!![position].setValue(currentQuantity - 1)
            products.value!![index].stockLevel++
        } else {
            bagProducts.value!!.removeAt(position)
        }

        products.value = products.value
        bagProducts.value = bagProducts.value

    }

    fun deleteFromBag(position: Int){
        val product = bagProducts.value!![position].key
        val index = products.value!!.indexOf(product)
        products.value!![index].stockLevel += bagProducts.value!![position].value
        total.value = total.value!!.subtract(product.price.multiply(BigDecimal(bagProducts.value!![position].value)))
        bagProducts.value!!.removeAt(position)

        products.value = products.value
        bagProducts.value = bagProducts.value
    }

    fun addToBag(position: Int){
        var product = products.value?.get(position)
        product!!.stockLevel -= product!!.quantity
        total.value = total.value!!.add(product.price.multiply(BigDecimal(product.quantity)))

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

    fun buyItems(paymentData: PaymentData) {

        var boughtItems: MutableList<BoughtItemModel> = ArrayList()

        for(entry in bagProducts.value!!){
            val product = entry.key
            val quantity = entry.value
            val boughtItemModel = BoughtItemModel(product, quantity)
            boughtItems.add(boughtItemModel)
        }

        payments.value!!.add(PaymentModel(paymentData, boughtItems))

        bagProducts.value!!.clear()
        total.value = BigDecimal.ZERO


        bagProducts.value = bagProducts.value
        payments.value = payments.value
        total.value = total.value
        Log.d("PAYMENTS", "Payment done")
        Log.d("PAYMENTS", bagProducts.value!!.size.toString())
        Log.d("PAYMENTS", total.value!!.toString())

    }

}