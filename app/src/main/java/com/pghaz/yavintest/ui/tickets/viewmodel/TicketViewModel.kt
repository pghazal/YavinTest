package com.pghaz.yavintest.ui.tickets.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.model.toTicketsWithQuantity
import com.pghaz.yavintest.model.yavin.PaymentRequest
import com.pghaz.yavintest.model.yavin.Ticket
import com.pghaz.yavintest.repo.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    application: Application,
    private val ticketRepository: TicketRepository
) : AndroidViewModel(application) {

    val cartCount = MutableLiveData(0)
    val payment = MutableSharedFlow<PaymentRequest>()

    val ticketsLiveData = ticketRepository.localTicketSource.getAllTickets.asLiveData()

    private fun cacheTickets(tickets: List<TicketWithQuantity>) =
        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.localTicketSource.insertTickets(tickets)
        }

    fun fetchTickets() {
        // TODO: get from json file ?
        val items = mutableListOf<Ticket>()
        items.add(
            Ticket(
                id = 1,
                title = "Single Journey Ticket",
                amount = "110"
            )
        )

        items.add(
            Ticket(
                id = 2,
                title = "Day Ticket",
                amount = "250"
            )
        )

        items.add(
            Ticket(
                id = 3,
                title = "Week Ticket",
                amount = "1200"
            )
        )

        val fetchedTickets = items.toTicketsWithQuantity()

        cacheTickets(fetchedTickets)
    }

    fun updateTicket(ticket: TicketWithQuantity) {
        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.localTicketSource.updateTicket(ticket)
        }
    }

    private fun updateTickets(tickets: List<TicketWithQuantity>) {
        viewModelScope.launch(Dispatchers.IO) {
            ticketRepository.localTicketSource.updateTickets(tickets)
        }
    }

    fun initCart() {
        ticketsLiveData.value?.let { tickets ->
            var quantity = 0
            for (ticket in tickets) {
                quantity += ticket.quantity
            }

            cartCount.value = quantity
        }
    }

    fun addToCart(ticket: TicketWithQuantity) {
        val newItem = ticket.copy(quantity = ticket.quantity + 1)
        updateTicket(newItem)

        cartCount.value = cartCount.value?.plus(1)
    }

    fun removeFromCart(ticket: TicketWithQuantity) {
        if (ticket.quantity > 0) {
            val newItem = ticket.copy(quantity = ticket.quantity - 1)
            updateTicket(newItem)

            if (cartCount.value!! <= 1) {
                cartCount.value = 0
            } else {
                cartCount.value = cartCount.value?.minus(1)
            }
        }
    }

    fun clearCart() {
        ticketsLiveData.value?.let { tickets ->
            for (ticket in tickets) {
                ticket.quantity = 0
            }

            updateTickets(tickets)
        }

        cartCount.value = 0
    }

    fun launchPayment() {
        val paymentRequest = PaymentRequest()
        paymentRequest.apply {
            amount = calculateAmountInCts().toString()
            receiptTicket = generateTicketReceipt()
        }

        Timber.d(paymentRequest.toString())

        viewModelScope.launch {
            payment.emit(paymentRequest)
        }
    }

    private fun calculateAmountInCts(): Long {
        var amount = 0L

        ticketsLiveData.value?.let { tickets ->
            for (ticket in tickets) {
                amount += (ticket.quantity * ticket.amount.toLong())
            }
        }

        return amount
    }

    private fun generateTicketReceipt(): List<String> {
        val receipt = mutableListOf<String>()

        ticketsLiveData.value?.let { tickets ->
            for (ticket in tickets) {
                if (ticket.quantity > 0) {
                    receipt.add("> ${ticket.title} x${ticket.quantity}")
                }
            }
        }

        return receipt
    }
}