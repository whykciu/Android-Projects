package com.example.calculatortests
import java.math.BigDecimal
import java.math.RoundingMode

enum class BinaryOperators(val id: Int){
    ADD(R.id.addBtn){
        override fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal {
            return a.add(b)
        }
    },
    SUBTRACT(R.id.subtractBtn){
        override fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal {
            return a.subtract(b)
        }
    },
    MULTIPLY(R.id.multiplyBtn){
        override fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal {
            return a.multiply(b)
        }
    },
    DIVIDE(R.id.divideBtn){
        override fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal {
            return a.divide(b, 6, RoundingMode.HALF_UP)
        }
    },
    MODULO(R.id.moduloBtn){
        override fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal {
            return a % b
        }
    };

    abstract fun operate(a: BigDecimal, b: BigDecimal) : BigDecimal
}