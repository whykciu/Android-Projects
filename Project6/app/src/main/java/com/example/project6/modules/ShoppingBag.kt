package com.example.project6.modules

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class ShoppingBag : EmbeddedRealmObject {

    var products: RealmList<BagEntity> = realmListOf()
    var sumCost: Double = 0.0

    constructor(products: RealmList<BagEntity>){
        this.products = products
        for(p in this.products){
            sumCost += (p.quantity * p.product!!.price)
        }
    }

    constructor(){}


}