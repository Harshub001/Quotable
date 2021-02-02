package com.task.quotable.byrxjava.quotesbyrx.quoteuirx


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.databinding.FragmentQuotesBinding
import com.task.quotable.bycouroutines.adapter.QuotesListAdapter
import com.task.quotable.bycouroutines.adapter.footeradapter.NetworkLoadStateAdapter
import com.task.quotable.byrxjava.quotesbyrx.quotedatarx.QuoteInjectionRx
import io.reactivex.rxjava3.disposables.CompositeDisposable


class QuotesFragmentRx : Fragment() {

    private val rxQuotesDisposable = CompositeDisposable()

    private lateinit var rxQuotesBinding: FragmentQuotesBinding
    private lateinit var rxQuotesViewModel: QuoteViewModelRx
    private lateinit var rxQuotesAdapter: QuotesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rxQuotesBinding = FragmentQuotesBinding.inflate(inflater, container, false)

        val view = rxQuotesBinding.root

        setupRecyclerView()

        rxQuotesViewModel = ViewModelProvider(this, QuoteInjectionRx.provideRxViewModel(view.context)).get(
                QuoteViewModelRx::class.java)

        rxQuotesDisposable.add(rxQuotesViewModel.getRxQuotes().subscribe {
            rxQuotesAdapter.submitData(lifecycle, it)
        })

        setupProgressBar()
        setupRetryButton()

        return view
    }


    private fun setupRecyclerView() {
        rxQuotesAdapter = QuotesListAdapter()
        rxQuotesBinding.quoteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        rxQuotesBinding.quoteRecyclerView.setHasFixedSize(true)
        rxQuotesBinding.quoteRecyclerView.adapter = rxQuotesAdapter.withLoadStateFooter(
                footer = NetworkLoadStateAdapter { rxQuotesAdapter.retry() }
        )
    }

    private fun setupRetryButton() {
        rxQuotesBinding.quotesBtnRetry.setOnClickListener {
            rxQuotesAdapter.retry()
        }
    }

    private fun setupProgressBar() {
        rxQuotesAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                rxQuotesBinding.quotesBtnRetry.visibility = View.GONE
                rxQuotesBinding.quotesProgressBar.visibility = View.VISIBLE
            } else {
                rxQuotesBinding.quotesProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        rxQuotesBinding.quotesBtnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    } else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        rxQuotesDisposable.dispose()
        super.onDestroyView()
    }

}
