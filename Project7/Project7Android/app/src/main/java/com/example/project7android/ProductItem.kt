package com.example.project7android

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey

class ProductItem : RealmObject {
    @PersistedName("_id")
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var categoryId: Int = 0
    var price: Double = 0.0
}
