package com.example.project6.modules

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var surname: String = ""
    var nickname: String = ""
    var shoppingBag: ShoppingBag? = null

    constructor(){}

    constructor(name: String, surname: String, nickname: String){
        this.name = name
        this.surname = surname
        this.nickname = nickname
        this.shoppingBag = ShoppingBag()
    }

    constructor(name: String, surname: String, nickname: String, products: RealmList<BagEntity>){
        this.name = name
        this.surname = surname
        this.nickname = nickname
        this.shoppingBag = ShoppingBag(products)
    }

}