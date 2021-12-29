package com.pghaz.yavintest.ui.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketBinding
import com.pghaz.yavintest.model.Ticket
import com.pghaz.yavintest.utils.CurrencyUtils

class TicketsAdapter : RecyclerView.Adapter<TicketViewHolder>() {

    private val items = ArrayList<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = items[position]
        holder.binding.titleTextView.text = ticket.title
        holder.binding.amountTextView.text = CurrencyUtils.convertCtsToDecimal(ticket.amount)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<Ticket>, notify: Boolean = true) {
        items.clear()
        items.addAll(newItems)

        if (notify) {
            notifyDataSetChanged()
        }
    }
}