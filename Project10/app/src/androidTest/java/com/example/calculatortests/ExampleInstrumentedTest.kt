package com.example.calculatortests

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.text.DecimalFormat


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val decimalFormat = DecimalFormat("0.000000")

    //50 Functional Tests (black-box testing)

    @Test
    fun testClearAll() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.clearAllBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("0")))

    }

    @Test
    fun testClearOne() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.clearOneBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("1")))

    }

    @Test
    fun testClearOneDot() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.clearOneBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("0")))

    }

    @Test
    fun testDot() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("0.")))

    }

    @Test
    fun testMultipleDot() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("0.")))

    }

    @Test
    fun testIntAfterDot() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("0.1")))

    }


    @Test
    fun testAdditionOfTwoPositiveInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("3")))

    }

    @Test
    fun testAdditionOfTwoNegativeInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-3")))

    }

    @Test
    fun testAdditionOfTwoPositiveDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("4.6")))

    }

    @Test
    fun testAdditionOfTwoNegativeDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-4.6")))

    }

    @Test
    fun testAdditionOfTwoPositiveIntAndNegativeInt() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-1")))

    }

    @Test
    fun testAdditionOfTwoPositiveDecimalAndNegativeDecimal() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-2.2")))

    }

    @Test
    fun testAdditionOfTwoPositiveIntAndPositiveDecimal() {

        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("7.3")))

    }

    @Test
    fun testAdditionOfTwoNegativeIntAndNegativeDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-7.9")))

    }

    @Test
    fun testAdditionOfTwoPositiveIntAndNegativeDecimal() {

        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("1.1")))

    }

    @Test
    fun testAdditionOfTwoNegativeIntAndPositiveDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.addBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-1.7")))

    }

    @Test
    fun testSubtractionOfTwoPositiveInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-1")))

    }

    @Test
    fun testSubtractionOfTwoNegativeInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("1")))

    }

    @Test
    fun testSubtractionOfTwoPositiveDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-2.2")))

    }

    @Test
    fun testSubtractionOfTwoNegativeDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("2.2")))

    }

    @Test
    fun testSubtractionOfTwoPositiveIntAndNegativeInt() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("3")))

    }

    @Test
    fun testSubtractionOfTwoPositiveDecimalAndNegativeDecimal() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("2.4")))

    }

    @Test
    fun testSubtractionOfTwoPositiveIntAndPositiveDecimal() {

        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("2.7")))

    }

    @Test
    fun testSubtractionOfTwoNegativeIntAndNegativeDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("1.9")))

    }

    @Test
    fun testSubtractionOfTwoPositiveIntAndNegativeDecimal() {

        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("10.9")))

    }

    @Test
    fun testSubtractionOfTwoNegativeIntAndPositiveDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.subtractBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-4.3")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("2")))

    }

    @Test
    fun testMultiplicationOfTwoNegativeInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("2")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("4.08")))

    }

    @Test
    fun testMultiplicationOfTwoNegativeDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("4.08")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveIntAndNegativeInt() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-2")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveDecimalAndNegativeDecimal() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-0.23")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveIntAndPositiveDecimal() {

        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("11.5")))

    }

    @Test
    fun testMultiplicationOfTwoNegativeIntAndNegativeDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("14.7")))

    }

    @Test
    fun testMultiplicationOfTwoPositiveIntAndNegativeDecimal() {

        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-29.4")))

    }

    @Test
    fun testMultiplicationOfTwoNegativeIntAndPositiveDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.multiplyBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("-3.9")))

    }

    @Test
    fun testDivisionOfTwoPositiveInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("0.5"))))

    }

    @Test
    fun testDivisionOfTwoNegativeInts() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("0.5"))))

    }

    @Test
    fun testDivisionOfTwoPositiveDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("0.352941"))))

    }

    @Test
    fun testDivisionOfTwoNegativeDecimals() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("0.352941"))))

    }

    @Test
    fun testDivisionOfTwoPositiveIntAndNegativeInt() {

        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("-0.5"))))

    }

    @Test
    fun testDivisionOfTwoPositiveDecimalAndNegativeDecimal() {

        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("-0.043478"))))

    }

    @Test
    fun testDivisionOfTwoPositiveIntAndPositiveDecimal() {

        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput(formatOutput("2.173913")))))

    }

    @Test
    fun testDivisionOfTwoNegativeIntAndNegativeDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("0.612245"))))

    }

    @Test
    fun testDivisionOfTwoPositiveIntAndNegativeDecimal() {

        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("-1.22449"))))

    }

    @Test
    fun testDivisionOfTwoNegativeIntAndPositiveDecimal() {

        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.negativeBtn)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.dotBtn)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText(formatOutput("-2.307692"))))

    }

    @Test
    fun testModulo(){

        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.moduloBtn)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("1")))

    }

    @Test
    fun testDivisionByZero(){

        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.divideBtn)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.equalsBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("ERROR")))

    }

    @Test
    fun testPower(){

        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.powerBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("186624")))

    }

    @Test
    fun testLog(){

        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.logBtn)).perform(click())
        onView(withId(R.id.resultView)).check(matches(withText("4.56434819146783610221973503939807415008544921875")))

    }

    private fun formatOutput(output: String): String {

        return decimalFormat.format(BigDecimal(output))

    }


}