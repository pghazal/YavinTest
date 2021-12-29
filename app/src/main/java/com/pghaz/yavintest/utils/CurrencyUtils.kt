package com.pghaz.yavintest.utils

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {

    fun convertCtsToDecimal(cts: String?): String {
        if(cts == null) {
            return "N/A"
        }

        val amountInCts = cts.toDouble()

        val n: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val value = amountInCts / 100.0

        return n.format(value)
    }
}