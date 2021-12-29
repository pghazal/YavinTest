package com.pghaz.yavintest.ui.tickets.adapter

import com.pghaz.yavintest.model.Ticket

interface TicketClickListener {

    fun onTicketClicked(ticket: Ticket)
}