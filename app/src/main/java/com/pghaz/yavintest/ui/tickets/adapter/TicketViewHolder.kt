package com.pghaz.yavintest.ui.tickets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketBinding
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.utils.CurrencyUtils

class TicketViewHolder(private val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: TicketWithQuantity, listener: TicketClickListener) {
        binding.titleTextView.text = ticket.title
        binding.amountTextView.text = CurrencyUtils.convertCtsToDecimal(ticket.amount)

        binding.quantityTextView.text = ticket.quantity.toString()

        binding.addButton.setOnClickListener {
            listener.addTicket(ticket)
        }

        binding.removeButton.setOnClickListener {
            listener.removeTicket(ticket)
        }
    }
}