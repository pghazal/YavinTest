package com.pghaz.yavintest.ui.tickets.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pghaz.yavintest.model.Ticket

class TicketViewModel(application: Application) : AndroidViewModel(application) {

    val tickets = MutableLiveData<List<Ticket>>()

    fun fetchTickets() {
        // TODO: get from json file
        val items = mutableListOf<Ticket>()
        items.add(Ticket(
            title = "Single Journey",
            amount = "110"
        ))

        items.add(Ticket(
            title = "Day Ticket",
            amount = "250"
        ))

        items.add(Ticket(
            title = "Week Ticket",
            amount = "1200"
        ))

        tickets.value = items
    }
}