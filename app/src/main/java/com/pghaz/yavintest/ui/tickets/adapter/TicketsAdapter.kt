package com.pghaz.yavintest.ui.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketBinding
import com.pghaz.yavintest.model.TicketWithQuantity

class TicketsAdapter(private val listener: TicketClickListener) : RecyclerView.Adapter<TicketViewHolder>() {

    private val items = ArrayList<TicketWithQuantity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = items[position]
        holder.bind(ticket, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<TicketWithQuantity>, notify: Boolean = true) {
        items.clear()
        items.addAll(newItems)

        if (notify) {
            notifyDataSetChanged()
        }
    }
}