package com.pghaz.yavintest.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pghaz.yavintest.databinding.FragmentTicketsBinding
import com.pghaz.yavintest.model.PaymentRequest
import com.pghaz.yavintest.ui.payment.PaymentResultContract
import com.pghaz.yavintest.ui.tickets.adapter.TicketsAdapter
import com.pghaz.yavintest.ui.tickets.viewmodel.TicketViewModel

class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val ticketViewModel: TicketViewModel by viewModels()
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
        initObservers()

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

    private fun initObservers() {
        ticketViewModel.tickets.observe(viewLifecycleOwner, { tickets ->
            adapter.update(tickets)
        })
        ticketViewModel.fetchTickets()
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