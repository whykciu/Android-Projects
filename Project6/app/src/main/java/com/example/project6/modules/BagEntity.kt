package com.example.project6.modules

import io.realm.kotlin.types.EmbeddedRealmObject

class BagEntity : EmbeddedRealmObject {

    var product: Product? = null
    var quantity: Int = 0

    constructor(){}

    constructor(product: Product, quantity: Int){
        this.product = product
        this.quantity = quantity
    }


}