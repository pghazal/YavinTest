package com.pghaz.yavintest.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pghaz.yavintest.databinding.FragmentReceiptsBinding
import com.pghaz.yavintest.ui.history.adapter.ReceiptsAdapter
import com.pghaz.yavintest.ui.history.viewmodel.ReceiptViewModel
import kotlinx.coroutines.launch

class ReceiptsFragment : Fragment() {

    private var _binding: FragmentReceiptsBinding? = null
    private val binding get() = _binding!!

    private val receiptsViewModel by activityViewModels<ReceiptViewModel>()

    private lateinit var adapter: ReceiptsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceiptsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObservers()
    }

    private fun initAdapter() {
        adapter = ReceiptsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        lifecycleScope.launch {
            receiptsViewModel.receiptsLiveData.observe(viewLifecycleOwner, { receipts ->
                if (receipts.isNotEmpty()) {
                    adapter.update(receipts)
                } else {
                    // TODO: show placeholder
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}