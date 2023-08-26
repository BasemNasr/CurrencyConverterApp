package com.currency.converter.utils

import com.currency.converter.data.models.response.CurrenciesData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object AppUtils {
    fun getCurrencyValue(key: String, currencyData: CurrenciesData?): Double? {
        return when (key) {
            "USD" -> {
                currencyData?.USD
            }

            "AUD" -> {
                currencyData?.AUD
            }

            "BGN" -> {
                currencyData?.BGN
            }

            "BRL" -> {
                currencyData?.BRL
            }

            "CAD" -> {
                currencyData?.CAD
            }

            "CHF" -> {
                currencyData?.CHF
            }

            "CNY" -> {
                currencyData?.CNY
            }

            "CZK" -> {
                currencyData?.CZK
            }

            "DKK" -> {
                currencyData?.DKK
            }

            "EUR" -> {
                currencyData?.EUR
            }

            "GBP" -> {
                currencyData?.GBP
            }

            "HKD" -> {
                currencyData?.HKD
            }

            "HRK" -> {
                currencyData?.HRK
            }

            "HUF" -> {
                currencyData?.HUF
            }

            "IDR" -> {
                currencyData?.IDR
            }

            "ILS" -> {
                currencyData?.ILS
            }

            "INR" -> {
                currencyData?.INR
            }

            "ISK" -> {
                currencyData?.ISK
            }

            "JPY" -> {
                currencyData?.JPY
            }

            "KRW" -> {
                currencyData?.KRW
            }

            "MXN" -> {
                currencyData?.MXN
            }

            "MYR" -> {
                currencyData?.MYR
            }

            "NOK" -> {
                currencyData?.NOK
            }

            "NZD" -> {
                currencyData?.NZD
            }

            "PHP" -> {
                currencyData?.PHP
            }

            "PLN" -> {
                currencyData?.PLN
            }

            "RON" -> {
                currencyData?.RON
            }

            "RUB" -> {
                currencyData?.RUB
            }

            "SEK" -> {
                currencyData?.SEK
            }

            "SGD" -> {
                currencyData?.THB
            }

            "THB" -> {
                currencyData?.THB
            }

            "TRY" -> {
                currencyData?.TRY
            }

            "ZAR" -> {
                currencyData?.ZAR
            }

            else -> {
                0.0
            }
        }
    }


    fun gettingCurrentData(): String {
        val c: Date = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return df.format(c)
    }

    fun gettingDateBefore3Days(currentDate: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse(currentDate)
        val newDate: Date = Date(myDate.time - 259200000L) // 3 * 24 * 60 * 60 * 1000
        val date: String = df.format(newDate)
        return date
    }
    fun gettingDateBefore2Days(currentDate: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse(currentDate)
        val newDate: Date = Date(myDate.time - 172800000L) //2 * 24 * 60 * 60 * 1000
        val date: String = df.format(newDate)
        return date
    }

    fun gettingYesterdayDate(currentDate: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val myDate: Date = df.parse(currentDate)
        val newDate: Date = Date(myDate.time - 86400000L) // 1 * 24 * 60 * 60 * 1000
        return df.format(newDate)
    }

}