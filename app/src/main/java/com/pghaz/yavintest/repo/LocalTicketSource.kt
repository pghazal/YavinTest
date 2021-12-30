package com.pghaz.yavintest.repo

import com.pghaz.yavintest.database.TicketsDao
import com.pghaz.yavintest.model.TicketWithQuantity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalTicketSource @Inject constructor(private val ticketsDao: TicketsDao) {

    val getAllTickets: Flow<List<TicketWithQuantity>> = ticketsDao.getAll()

    suspend fun insertTicket(ticket: TicketWithQuantity) {
        ticketsDao.insert(ticket)
    }

    suspend fun insertTickets(tickets: List<TicketWithQuantity>) {
        ticketsDao.insert(tickets)
    }

    suspend fun updateTicket(ticket: TicketWithQuantity) {
        ticketsDao.update(ticket)
    }

    suspend fun updateTickets(tickets: List<TicketWithQuantity>) {
        ticketsDao.update(tickets)
    }
}