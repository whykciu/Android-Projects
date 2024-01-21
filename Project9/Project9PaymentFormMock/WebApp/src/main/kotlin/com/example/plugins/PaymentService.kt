package com.example.plugins

import java.math.BigDecimal

object PaymentService {

    private var userBankAccount = BankAccountInfo("5527764928215882", "05/27", "108", BigDecimal(598.65))

    fun checkPayment(paymentData: PaymentData): String{
        val totalBigDecimal = BigDecimal(paymentData.total)
        if(paymentData.cardNumber == userBankAccount.cardNumber && paymentData.expireDate == userBankAccount.expireDate && paymentData.cvv == userBankAccount.cvv){
            if(userBankAccount.balance >= totalBigDecimal){
                userBankAccount.balance = userBankAccount.balance.subtract(totalBigDecimal)
                return "Success"
            }
            return "Error, insufficient funds"
        }
        return "Error, incorrect payment data"
    }

}