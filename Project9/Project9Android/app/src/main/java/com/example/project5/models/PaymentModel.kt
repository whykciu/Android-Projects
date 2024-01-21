package com.example.project5.models

data class PaymentModel(
    val paymentData: PaymentData,
    val boughtItems: List<BoughtItemModel>
)
