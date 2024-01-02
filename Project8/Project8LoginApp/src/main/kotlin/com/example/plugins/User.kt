package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val login: String,
    val password: String
)
