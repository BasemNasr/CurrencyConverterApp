package com.currency.converter.utils


import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppUtilsTest {

    @Test
    fun gettingDateBefore4Days() {
        val currentDate = "2023-08-26"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse("$currentDate")
        val newDate = Date(myDate.time - 345600000L)
        val date: String = df.format(newDate)

        assertEquals(date,"2023-08-22")
    }

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
    fun gettingDayBeforeYesterdayDate() {
        val currentDate = "2023-08-26"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse("$currentDate")
        val newDate = Date(myDate.time - 172800000L)
        val date: String = df.format(newDate)

        assertEquals(date,"2023-08-24")
    }
}