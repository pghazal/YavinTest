package com.pghaz.yavintest.ui.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemTicketEditorBinding
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.ui.tickets.adapter.TicketEditorViewHolder

class TicketsEditorAdapter(private val listener: TicketEditorListener) : RecyclerView.Adapter<TicketEditorViewHolder>() {

    private val items = ArrayList<TicketWithQuantity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketEditorViewHolder {
        val binding =
            ItemTicketEditorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TicketEditorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketEditorViewHolder, position: Int) {
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