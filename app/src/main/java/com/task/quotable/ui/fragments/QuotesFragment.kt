package com.task.quotable.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.adapter.QuoteListAdapter
import com.task.quotable.databinding.FragmentQuotesBinding
import com.task.quotable.service.QuotesAPIService
import com.task.quotable.ui.viewmodel.QuotesViewModel
import com.task.quotable.ui.viewmodel.QuotesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class QuotesFragment : Fragment() {

    private lateinit var viewModel: QuotesViewModel
    private lateinit var binding: FragmentQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentQuotesBinding.inflate(inflater, container, false).also {
        binding = it

    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
       val factory = QuotesViewModelFactory(QuotesAPIService.getApiService())
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)
        val quotesAdapter = QuoteListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = quotesAdapter
        lifecycleScope.launch {
            viewModel.quoteListData.collectLatest { pagedData ->
                quotesAdapter.submitData(pagedData)
            }
        }
    }
}