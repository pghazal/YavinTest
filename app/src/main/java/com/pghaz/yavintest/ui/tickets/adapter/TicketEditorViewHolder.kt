package com.pghaz.yavintest.ui.tickets.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketEditorBinding
import com.pghaz.yavintest.model.TicketWithQuantity

class TicketEditorViewHolder(private val binding: ItemTicketEditorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(ticket: TicketWithQuantity, listener: TicketEditorListener) {
        binding.titleEditText.setText(ticket.title)
        binding.amountEditText.setText(ticket.amount)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val amount = binding.amountEditText.text.toString()

            if (title.isNotEmpty() && amount.isNotEmpty()) {
                val newTicket = ticket.copy(
                    title = title,
                    amount = amount
                )

                listener.updateTicket(newTicket)
            } else {
                listener.showEditNotAllowed()
            }
        }
    }
}