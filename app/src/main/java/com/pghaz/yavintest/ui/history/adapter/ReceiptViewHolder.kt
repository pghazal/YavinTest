package com.pghaz.yavintest.ui.history.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pghaz.yavintest.databinding.ItemReceiptBinding
import com.pghaz.yavintest.model.Receipt
import com.pghaz.yavintest.utils.CurrencyUtils

class ReceiptViewHolder(private val binding: ItemReceiptBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(receipt: Receipt) {
        binding.transactionIdTextView.text = receipt.transactionId
        binding.amountTextView.text =  "Amount = ${CurrencyUtils.convertCtsToDecimal(receipt.amount)}"
        binding.totalAmountTextView.text = "Total = ${CurrencyUtils.convertCtsToDecimal(receipt.totalAmount)}"
        binding.statusTextView.text = receipt.status?.name?.uppercase()
    }
}