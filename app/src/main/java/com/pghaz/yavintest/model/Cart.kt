package com.pghaz.yavintest.model

data class Cart(
    var items: List<Ticket> = emptyList(),
    var totalAmount: Double = 0.0
) {
}