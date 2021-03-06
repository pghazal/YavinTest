package com.pghaz.yavintest.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pghaz.yavintest.R
import com.pghaz.yavintest.databinding.FragmentTicketsBinding
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.model.toReceipt
import com.pghaz.yavintest.model.yavin.PaymentRequest
import com.pghaz.yavintest.model.yavin.Status
import com.pghaz.yavintest.ui.history.viewmodel.ReceiptViewModel
import com.pghaz.yavintest.ui.payment.PaymentResultContract
import com.pghaz.yavintest.ui.tickets.adapter.TicketClickListener
import com.pghaz.yavintest.ui.tickets.adapter.TicketsAdapter
import com.pghaz.yavintest.ui.tickets.viewmodel.TicketViewModel
import com.pghaz.yavintest.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment : Fragment(), TicketClickListener {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val ticketViewModel by activityViewModels<TicketViewModel>()
    private val receiptViewModel by activityViewModels<ReceiptViewModel>()
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
    }

    private fun initAdapter() {
        adapter = TicketsAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        loadTickets()
        initCart()

        ticketViewModel.cartCount.observe(viewLifecycleOwner, { count ->
            if (count > 0) {
                binding.cartButton.visibility = View.VISIBLE
                binding.cartButton.setOnClickListener {
                    ticketViewModel.launchPayment()
                }
            } else {
                binding.cartButton.visibility = View.GONE
                binding.cartButton.setOnClickListener(null)
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            ticketViewModel.payment.collectLatest { paymentRequest ->
                startPaymentOnYavinPay(paymentRequest)
            }
        }
    }

    private fun loadTickets() {
        lifecycleScope.launch {
            ticketViewModel.ticketsLiveData.observe(viewLifecycleOwner, { cachedTickets ->
                if (cachedTickets.isNotEmpty()) {
                    adapter.update(cachedTickets)
                } else {
                    ticketViewModel.fetchTickets()
                }
            })
        }
    }

    private fun initCart() {
        lifecycleScope.launch {
            ticketViewModel.ticketsLiveData.observeOnce(viewLifecycleOwner, {
                ticketViewModel.initCart()
            })
        }
    }

    private fun startPaymentOnYavinPay(payment: PaymentRequest) {
        resultLauncher.launch(payment)
    }

    private var resultLauncher =
        registerForActivityResult(PaymentResultContract()) { paymentResponse ->
            // Payment succeed, we can clear the cart
            if (paymentResponse.status == Status.OK && paymentResponse.transactionId != null) {
                Snackbar.make(
                    requireActivity().findViewById(R.id.content_main),
                    "Payment succeed",
                    Snackbar.LENGTH_SHORT
                ).show()

                ticketViewModel.clearCart()

                receiptViewModel.saveReceipt(paymentResponse.toReceipt())
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addTicket(ticket: TicketWithQuantity) {
        ticketViewModel.addToCart(ticket)
    }

    override fun removeTicket(ticket: TicketWithQuantity) {
        ticketViewModel.removeFromCart(ticket)
    }
}