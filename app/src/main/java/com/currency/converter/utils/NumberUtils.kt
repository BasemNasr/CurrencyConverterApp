package com.currency.converter.utils

object NumberUtils {
    fun roundTo2DecimalAsString(input: Double): String {
        return String.format("%.2f", input)
    }
}