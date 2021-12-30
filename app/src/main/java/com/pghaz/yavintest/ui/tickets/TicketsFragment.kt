package com.pghaz.yavintest.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pghaz.yavintest.databinding.FragmentTicketsBinding
import com.pghaz.yavintest.model.TicketWithQuantity
import com.pghaz.yavintest.model.yavin.PaymentRequest
import com.pghaz.yavintest.model.yavin.Status
import com.pghaz.yavintest.ui.payment.PaymentResultContract
import com.pghaz.yavintest.ui.tickets.adapter.TicketClickListener
import com.pghaz.yavintest.ui.tickets.adapter.TicketsAdapter
import com.pghaz.yavintest.ui.tickets.viewmodel.TicketViewModel
import com.pghaz.yavintest.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class TicketsFragment : Fragment(), TicketClickListener {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    private val ticketViewModel by viewModels<TicketViewModel>()
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

        binding.clearButton.setOnClickListener {
            ticketViewModel.clearCart()
        }
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
                binding.clearButton.visibility = View.VISIBLE
                binding.cartButton.visibility = View.VISIBLE
                binding.cartButton.setOnClickListener {
                    ticketViewModel.launchPayment()
                }
            } else {
                binding.clearButton.visibility = View.GONE
                binding.cartButton.visibility = View.GONE
                binding.cartButton.setOnClickListener(null)
            }
        })

        ticketViewModel.payment.observe(viewLifecycleOwner, { paymentRequest ->
            startPaymentOnYavinPay(paymentRequest)
        })
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
            Toast.makeText(requireContext(), paymentResponse.toString(), Toast.LENGTH_SHORT).show()
            Timber.d(paymentResponse.toString())

            // Payment succeed, we can clear the cart
            if (paymentResponse.status == Status.OK) {
                // TODO: add snackbar to indicate payment succeed
                ticketViewModel.clearCart()
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