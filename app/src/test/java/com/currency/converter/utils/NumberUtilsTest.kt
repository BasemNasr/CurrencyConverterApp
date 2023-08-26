package com.currency.converter.utils

import junit.framework.TestCase
import org.junit.Assert.*

import org.junit.Test

class NumberUtilsTest {

    @Test
    fun roundTo2DecimalAsString() {
        val input = 1.564598942
        val output = String.format("%.2f", input)
        TestCase.assertEquals(output, "1.56")

    }
}