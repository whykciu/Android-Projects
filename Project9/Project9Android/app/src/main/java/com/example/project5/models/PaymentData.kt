package com.example.project5.models

import java.math.BigDecimal

data class PaymentData(
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    val total: BigDecimal
)
