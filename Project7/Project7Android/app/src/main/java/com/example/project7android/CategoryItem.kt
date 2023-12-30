package com.example.project7android

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey

class CategoryItem : RealmObject {
    @PersistedName("_id")
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
}
