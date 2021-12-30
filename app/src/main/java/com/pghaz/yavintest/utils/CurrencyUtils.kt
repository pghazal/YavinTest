package com.pghaz.yavintest.utils

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {

    fun convertCtsToDecimal(cts: String?, addCurrency: Boolean = true): String {
        if (cts == null) {
            return "N/A"
        }

        val amountInCts = cts.toDouble()

        val value = amountInCts / 100.0

        return if (addCurrency) {
            val numberFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            numberFormat.format(value)
        } else {
            value.toString()
        }
    }
}