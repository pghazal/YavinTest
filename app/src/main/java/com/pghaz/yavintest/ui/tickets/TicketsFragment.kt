package com.pghaz.yavintest.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pghaz.yavintest.databinding.FragmentTicketsBinding
import com.pghaz.yavintest.model.PaymentRequest
import com.pghaz.yavintest.model.Ticket
import com.pghaz.yavintest.ui.payment.PaymentResultContract
import com.pghaz.yavintest.ui.tickets.adapter.TicketsAdapter

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TicketsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        addFakeData()

        binding.buttonFirst.setOnClickListener {
            val paymentRequest = PaymentRequest()
            paymentRequest.apply {
                amount = "50"
            }
            startPaymentOnYavinPay(paymentRequest)
        }
    }

    private fun initAdapter() {
        adapter = TicketsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addFakeData() {
        val items = ArrayList<Ticket>()
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

        adapter.update(items)
    }

    private var resultLauncher = registerForActivityResult(PaymentResultContract()) { result ->
        Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun startPaymentOnYavinPay(payment: PaymentRequest) {
        resultLauncher.launch(payment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}