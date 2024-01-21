package com.example.plugins

import java.math.BigDecimal

data class BankAccountInfo(
    val cardNumber: String,
    val expireDate: String,
    val cvv: String,
    var balance: BigDecimal
)
