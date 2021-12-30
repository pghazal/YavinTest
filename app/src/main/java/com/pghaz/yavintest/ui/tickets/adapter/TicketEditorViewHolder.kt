package com.pghaz.yavintest.ui.tickets.adapter

import android.text.Editable
import android.text.TextWatcher
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
                binding.saveButton.isEnabled = false
            } else {
                listener.showEditNotAllowed()
            }
        }

        binding.titleEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.saveButton.isEnabled = shouldEnableSaveButton(
                    ticket,
                    s.toString(),
                    binding.amountEditText.text.toString()
                )
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.amountEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.saveButton.isEnabled = shouldEnableSaveButton(
                    ticket,
                    binding.titleEditText.text.toString(),
                    s.toString()
                )
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun shouldEnableSaveButton(
        ticket: TicketWithQuantity,
        editTitle: String,
        editAmount: String
    ): Boolean {
        return editTitle != ticket.title || editAmount != ticket.amount
    }
}