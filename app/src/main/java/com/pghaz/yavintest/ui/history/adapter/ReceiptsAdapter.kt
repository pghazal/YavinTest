package com.pghaz.yavintest.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemReceiptBinding
import com.pghaz.yavintest.model.Receipt

class ReceiptsAdapter : RecyclerView.Adapter<ReceiptViewHolder>() {

    private val items = ArrayList<Receipt>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding = ItemReceiptBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReceiptViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val receipt = items[position]
        holder.bind(receipt)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<Receipt>, notify: Boolean = true) {
        items.clear()
        items.addAll(newItems)

        if (notify) {
            notifyDataSetChanged()
        }
    }
}