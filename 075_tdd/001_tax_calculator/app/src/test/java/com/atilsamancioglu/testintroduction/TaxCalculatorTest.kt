package com.atilsamancioglu.testintroduction

import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class TaxCalculatorTest {

    private lateinit var tax : TaxCalculator

    @Before
    fun setup() {
        tax = TaxCalculator()
    }

    @After
    fun teardown() {
    }

    @Test
    fun test_netIncome() {
        val netIncome = tax.calculateNetIncome(100.0,0.1)
        assertThat(netIncome).isEqualTo(90)
    }

    @Test
    fun test_netTax() {
        val netTax = tax.calculateTax(100.0,0.1)
        assertThat(netTax).isEqualTo(10)
    }
}