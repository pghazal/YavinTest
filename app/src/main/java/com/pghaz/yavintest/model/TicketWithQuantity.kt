package com.pghaz.yavintest.model

import com.pghaz.yavintest.model.yavin.Ticket

data class TicketWithQuantity(
    var id: Int,
    var amount: String,
    var title: String,
    var quantity: Int = 0
)


fun Ticket.toTicketWithQuantity(): TicketWithQuantity {
    return TicketWithQuantity(id, amount, title, 0)
}

fun List<Ticket>.toTicketsWithQuantity(): List<TicketWithQuantity> {
    val items = mutableListOf<TicketWithQuantity>()
    for (ticket in this) {
        items.add(ticket.toTicketWithQuantity())
    }

    return items
}