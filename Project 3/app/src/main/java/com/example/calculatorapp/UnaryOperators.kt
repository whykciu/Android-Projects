package com.example.calculatorapp

import java.math.BigDecimal

enum class UnaryOperators(val id: Int){
    NEGATIVE(R.id.negativeBtn){
        override fun operate(a: BigDecimal): BigDecimal {
            return a.negate()
        }
    },
    POWER(R.id.powerBtn){
        override fun operate(a: BigDecimal): BigDecimal {
            return a.pow(2)
        }
    },
    LOG(R.id.logBtn){
        override fun operate(a: BigDecimal): BigDecimal {
            if(a <= BigDecimal(0)){
                throw ArithmeticException()
            }
            return BigDecimal(Math.log(a.toDouble()))
        }
    };

    abstract fun operate(a: BigDecimal) : BigDecimal
}