package com.currency.converter.utils


import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppUtilsTest {

    @Test
    fun gettingDateBefore3Days() {
        val currentDate = "2023-08-26"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse("$currentDate")
        val newDate = Date(myDate.time - 259200000L)
        val date: String = df.format(newDate)

        assertEquals(date,"2023-08-23")
    }

    @Test
    fun gettingDateBefore2Days() {
        val currentDate = "2023-08-26"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse("$currentDate")
        val newDate = Date(myDate.time - 172800000L)
        val date: String = df.format(newDate)

        assertEquals(date,"2023-08-24")
    }

    @Test
    fun gettingYesterdayDate() {
        val currentDate = "2023-08-26"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse("$currentDate")
        val newDate = Date(myDate.time - 86400000L)
        val date: String = df.format(newDate)

        assertEquals(date,"2023-08-25")
    }
}