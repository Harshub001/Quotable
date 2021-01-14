package com.task.quotable.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.R
import com.task.quotable.adapter.QuoteListAdapter
import com.task.quotable.model.Result
import com.task.quotable.service.APIService
import com.task.quotable.viewmodel.QuoteViewModel
import com.task.quotable.viewmodel.QuoteViewModelFactory
import kotlinx.android.synthetic.main.activity_quote.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class QuotesActivity : AppCompatActivity() {

    lateinit var viewModel: QuoteViewModel
    lateinit var mainListAdapter: QuoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        setupViewModel()
        setupList()
        setupView()
    }


    private fun setupView() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                mainListAdapter.submitData(it)
            }
        }
    }


    private fun setupList() {
        mainListAdapter = QuoteListAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel =
                ViewModelProvider(
                        this,
                        QuoteViewModelFactory(APIService.getApiService())
                )[QuoteViewModel::class.java]
    }


}


