package com.example.project6.modules

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Category : RealmObject {

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""

    constructor(){}

    constructor(name: String){
        this.name = name
    }

}