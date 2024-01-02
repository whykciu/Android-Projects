package com.example.plugins

import kotlinx.serialization.Serializable

@Serializable
data class Login (
    val login: String,
    val password: String
)
