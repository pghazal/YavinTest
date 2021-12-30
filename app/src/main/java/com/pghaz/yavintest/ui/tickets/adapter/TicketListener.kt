package com.pghaz.yavintest.ui.tickets.adapter

import com.pghaz.yavintest.model.TicketWithQuantity

interface TicketClickListener {

    fun addTicket(ticket: TicketWithQuantity)
    fun removeTicket(ticket: TicketWithQuantity)
}

interface TicketEditorListener {
    fun updateTicket(ticket: TicketWithQuantity)
    fun showEditNotAllowed()
}