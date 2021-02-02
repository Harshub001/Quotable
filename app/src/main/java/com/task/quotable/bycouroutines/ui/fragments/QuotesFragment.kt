package com.task.quotable.bycouroutines.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.bycouroutines.adapter.QuotesListAdapter
import com.task.quotable.databinding.FragmentQuotesBinding
import com.task.quotable.bycouroutines.service.QuotesAPIService
import com.task.quotable.bycouroutines.ui.viewmodel.QuotesViewModel
import com.task.quotable.bycouroutines.ui.viewmodel.QuotesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class QuotesFragment : Fragment() {

    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var quotesAdapter: QuotesListAdapter
    private lateinit var quotesBinding: FragmentQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentQuotesBinding.inflate(inflater, container, false).also {
        quotesBinding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupQuoteViewModel()
        setupQuoteList()
        setupQuoteView()
        setupProgressBar()
        quotesBinding.quotesBtnRetry.setOnClickListener {
            quotesAdapter.retry()
        }
    }

    private fun setupQuoteView() {
     lifecycleScope.launch {
            quotesViewModel.quoteListData.collectLatest { pagedData ->
                quotesAdapter.submitData(pagedData)
            }
        }
    }

    private fun setupQuoteList() {
        quotesAdapter = QuotesListAdapter()
        quotesBinding.quoteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        quotesBinding.quoteRecyclerView.setHasFixedSize(true)
        quotesBinding.quoteRecyclerView.adapter = quotesAdapter
    }

    private fun setupQuoteViewModel() {
        val quoteFactory = QuotesViewModelFactory(QuotesAPIService.getQuoteApiService())
        quotesViewModel = ViewModelProvider(this, quoteFactory).get(QuotesViewModel::class.java)
       }

    private fun setupProgressBar() {
        quotesAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                quotesBinding.quotesBtnRetry.visibility = View.GONE
                quotesBinding.quotesProgressBar.visibility = View.VISIBLE
            } else {
                quotesBinding.quotesProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        quotesBinding.quotesBtnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    } else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
