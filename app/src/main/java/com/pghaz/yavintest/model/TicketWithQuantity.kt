package com.pghaz.yavintest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pghaz.yavintest.model.yavin.Ticket
import com.pghaz.yavintest.utils.Constants

@Entity(tableName = Constants.TICKETS_TABLE)
data class TicketWithQuantity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @ColumnInfo(name = "amount")
    var amount: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "quantity")
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