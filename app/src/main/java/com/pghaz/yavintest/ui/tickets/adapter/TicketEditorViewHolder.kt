package com.pghaz.yavintest.ui.tickets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketEditorBinding
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.utils.CurrencyUtils

class TicketEditorViewHolder(private val binding: ItemTicketEditorBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: TicketWithQuantity) {
        binding.titleEditText.setText(ticket.title)
        binding.amountEditText.setText(CurrencyUtils.convertCtsToDecimal(ticket.amount, false))
    }
}