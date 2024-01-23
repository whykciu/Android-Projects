package com.example.calculatortests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.math.BigDecimal
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val resultView : TextView
        get() = findViewById(R.id.resultView)

    private val operatorView : TextView
        get() = findViewById(R.id.operatorView)

    private var state : State = State.NUMBER
    private var binaryOperation = false
    private lateinit var lastBinaryOperator : BinaryOperators
    private lateinit var firstNumber : BigDecimal
    private lateinit var secondNumber : BigDecimal

    fun handleError(){

        state = State.ERROR
        resultView.text = "ERROR"
        binaryOperation = false
        operatorView.text = ""

    }

    fun initializeNumberButtons(){

        val numbers = NumberBtn.values()

        for(num in numbers){

            var btn = findViewById<Button>(num.id)
            btn.setOnClickListener {

                when(state){

                    State.BINARY_OPERATOR, State.EQUALS, State.ERROR -> {
                        state = State.NUMBER
                        resultView.text = "${btn.text}"
                    }

                    State.NUMBER, State.DOT -> {
                        if(resultView.text.length < 16){
                            val decimalValue = BigDecimal("${resultView.text}${btn.text}")
                            resultView.text = decimalValue.toString()
                        }
                    }

                }

            }
        }

    }

    fun initializeBinaryOperatorsButtons(){

        val binaryOperators = BinaryOperators.values()

        for(binary in binaryOperators){

            var btn = findViewById<Button>(binary.id)
            btn.setOnClickListener {

                if(state != State.ERROR){

                    try {

                        if(state == State.NUMBER && binaryOperation){
                            secondNumber = BigDecimal(resultView.text.toString())
                            val result = lastBinaryOperator.operate(firstNumber, secondNumber)
                            resultView.text = result.toString()
                        }

                        firstNumber = BigDecimal(resultView.text.toString())
                        lastBinaryOperator = binary
                        state = State.BINARY_OPERATOR
                        operatorView.text = btn.text
                        binaryOperation = true

                    } catch (e : ArithmeticException){
                        handleError()
                    }

                }

            }

        }

    }

    fun initializeUnaryOperatorsButtons(){

        val unaryOperators = UnaryOperators.values()

        for(unary in unaryOperators){

            var btn = findViewById<Button>(unary.id)
            btn.setOnClickListener {

                if(state != State.BINARY_OPERATOR && state != State.ERROR){

                    try{

                        val resultStr = if(resultView.text.toString() == ".") "0." else resultView.text.toString()
                        val result = unary.operate(BigDecimal(resultStr))
                        resultView.text = result.toString()
                        state = State.NUMBER

                    } catch (e : ArithmeticException){
                        handleError()
                    }

                }

            }

        }

    }

    fun initializeClearAllButton(){

        val clearAllBtn: Button = findViewById(R.id.clearAllBtn)

        clearAllBtn.setOnClickListener {

            state = State.NUMBER
            operatorView.text = ""
            resultView.text = "0"
            binaryOperation = false

        }

    }

    fun initializeClearOneButton(){

        val clearBtn: Button = findViewById(R.id.clearOneBtn)

        clearBtn.setOnClickListener {

            if(state != State.ERROR){

                if(resultView.text.length > 1){
                    resultView.text = resultView.text.dropLast(1)
                } else {
                    resultView.text = "0"
                }

                state = State.NUMBER

            }

        }

    }

    fun initializeEqualsButton(){

        val equalsBtn : Button = findViewById(R.id.equalsBtn)

        equalsBtn.setOnClickListener {

            if(binaryOperation){

                try {

                    val resultStr = if(resultView.text.toString() == ".") "0." else resultView.text.toString()
                    secondNumber = BigDecimal(resultStr)
                    val result = lastBinaryOperator.operate(firstNumber, secondNumber)
                    resultView.text = result.toString()
                    operatorView.text = ""
                    state = State.EQUALS
                    binaryOperation = false;

                } catch (e : ArithmeticException){
                    handleError()
                }

            }

        }

    }

    fun initializeDotButton(){

        val dotBtn : Button = findViewById(R.id.dotBtn)

        dotBtn.setOnClickListener {

            when(state){

                State.DOT -> {}

                State.NUMBER -> {
                    if(!resultView.text.toString().contains(".")){
                        resultView.text = "${resultView.text}."
                    }
                    state = State.DOT
                }

                State.BINARY_OPERATOR, State.EQUALS, State.ERROR -> {
                    state = State.DOT
                    resultView.text = "."
                }


            }

            if(state != State.DOT && !resultView.text.toString().contains(".")){
                state = State.DOT
                resultView.text = "${resultView.text}."
            }

        }

    }


    fun initializeButtons(){

        initializeNumberButtons()
        initializeBinaryOperatorsButtons()
        initializeUnaryOperatorsButtons()
        initializeClearAllButton()
        initializeClearOneButton()
        initializeEqualsButton()
        initializeDotButton()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeButtons()
    }



}