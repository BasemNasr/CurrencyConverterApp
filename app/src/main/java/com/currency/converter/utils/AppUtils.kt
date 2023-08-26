package com.currency.converter.utils

import com.currency.converter.data.models.response.CurrenciesData

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
}