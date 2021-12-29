package com.pghaz.yavintest.model

data class Cart(
    var items: List<TicketWithQuantity> = emptyList(),
    var totalAmount: Double = 0.0
) {
}