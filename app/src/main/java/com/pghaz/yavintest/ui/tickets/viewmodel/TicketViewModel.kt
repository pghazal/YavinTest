package com.pghaz.yavintest.ui.tickets.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pghaz.yavintest.model.yavin.Ticket
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.model.toTicketsWithQuantity
import com.pghaz.yavintest.model.yavin.PaymentRequest
import timber.log.Timber

class TicketViewModel(application: Application) : AndroidViewModel(application) {

    val tickets = MutableLiveData<List<TicketWithQuantity>>()
    val cartCount = MutableLiveData(0)
    val payment = MutableLiveData<PaymentRequest>()

    fun fetchTickets() {
        // TODO: get from json file
        val items = mutableListOf<Ticket>()
        items.add(
            Ticket(
                id = 1,
                title = "Single Journey",
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

        tickets.value = items.toTicketsWithQuantity()
    }

    fun addToCart(ticket: TicketWithQuantity) {
        tickets.value?.let {
            for (item in it) {
                if (item.id == ticket.id) {
                    item.quantity++
                    break
                }
            }
        }

        cartCount.value = cartCount.value?.plus(1)
    }

    fun removeFromCart(ticket: TicketWithQuantity) {
        tickets.value?.let {
            for (item in it) {
                if (item.id == ticket.id && item.quantity > 0) {
                    item.quantity--
                    if (item.quantity < 0) {
                        item.quantity = 0
                    }

                    if(cartCount.value!! <= 1) {
                        cartCount.value = 0
                    } else {
                        cartCount.value = cartCount.value?.minus(1)
                    }
                    break
                }
            }
        }
    }

    fun clearCart() {
        tickets.value?.let { tickets ->
            for (ticket in tickets) {
                ticket.quantity = 0
            }
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

        payment.value = paymentRequest
    }

    private fun calculateAmountInCts(): Long {
        var amount = 0L

        tickets.value?.let { tickets ->
            for (ticket in tickets) {
                amount += (ticket.quantity * ticket.amount.toLong())
            }
        }

        return amount
    }

    private fun generateTicketReceipt(): List<String> {
        val receipt = mutableListOf<String>()

        tickets.value?.let { tickets ->
            for (ticket in tickets) {
                receipt.add("> ${ticket.title} x${ticket.quantity}")
            }
        }

        return receipt
    }
}