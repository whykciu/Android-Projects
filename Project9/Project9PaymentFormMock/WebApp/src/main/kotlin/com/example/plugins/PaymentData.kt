package com.example.plugins

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class PaymentData(
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val total: Double
)
