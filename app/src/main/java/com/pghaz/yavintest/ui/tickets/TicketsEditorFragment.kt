package com.pghaz.yavintest.ui.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pghaz.yavintest.databinding.FragmentTicketsEditorBinding
import com.pghaz.yavintest.ui.tickets.adapter.TicketsEditorAdapter
import com.pghaz.yavintest.ui.tickets.viewmodel.TicketViewModel

class TicketsEditorFragment : Fragment() {
    private var param1: String? = null

    private var _binding: FragmentTicketsEditorBinding? = null
    private val binding get() = _binding!!

    private val ticketViewModel by activityViewModels<TicketViewModel>()

    private lateinit var adapter: TicketsEditorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketsEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObservers()
    }

    private fun initAdapter() {
        adapter = TicketsEditorAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        ticketViewModel.ticketsLiveData.observe(viewLifecycleOwner, { tickets ->
            if (tickets.isNotEmpty()) {
                adapter.update(tickets)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}