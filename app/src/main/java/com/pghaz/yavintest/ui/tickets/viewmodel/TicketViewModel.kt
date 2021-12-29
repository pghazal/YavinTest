package com.pghaz.yavintest.ui.tickets.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pghaz.yavintest.model.Ticket
import com.pghaz.yavintest.model.yavin.PaymentRequest
import timber.log.Timber

class TicketViewModel(application: Application) : AndroidViewModel(application) {

    val tickets = MutableLiveData<List<Ticket>>()
    val cartCount = MutableLiveData<Int>()
    val payment = MutableLiveData<PaymentRequest>()

    private val cartMap = HashMap<Int, Int>()

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

        tickets.value = items
    }

    fun updateCart(ticket: Ticket) {
        if (cartMap.containsKey(ticket.id)) {
            val quantity = cartMap[ticket.id]!!
            cartMap[ticket.id] = quantity + 1
        } else {
            cartMap[ticket.id] = 1
        }

        Timber.d("Quantity of ${ticket.id} = ${cartMap[ticket.id]}")

        var count = 0
        cartMap.forEach { (_, quantity) ->
            count += quantity
        }

        cartCount.value = count
    }

    fun clearCart() {
        cartMap.clear()
        cartCount.value = 0
    }

    fun launchPayment() {
        val paymentRequest = PaymentRequest()
        paymentRequest.apply {
            amount = calculateAmountInCts().toString()
        }

        payment.value = paymentRequest
    }

    private fun calculateAmountInCts(): Long {
        var amount = 0L

        tickets.value?.let { tickets ->
            for (ticket in tickets) {
                if (cartMap.containsKey(ticket.id)) {
                    val quantity = cartMap[ticket.id]
                    amount += (quantity!! * ticket.amount.toLong())
                }
            }
        }

        return amount
    }
}