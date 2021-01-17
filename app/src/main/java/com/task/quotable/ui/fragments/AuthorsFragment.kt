package com.task.quotable.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingSource
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.adapter.AuthorListAdapter
import com.task.quotable.data.datasource.AuthorDataSource
import com.task.quotable.data.datasource.QuoteDataSource
import com.task.quotable.databinding.FragmentAuthorBinding
import com.task.quotable.service.AuthorAPIService
import com.task.quotable.service.QuotesAPIService
import com.task.quotable.ui.viewmodel.AuthorsViewModel
import com.task.quotable.ui.viewmodel.AuthorsViewModelFactory
import kotlinx.android.synthetic.main.fragment_author.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AuthorsFragment : Fragment() {

    private lateinit var viewModel: AuthorsViewModel
    private lateinit var binding: FragmentAuthorBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentAuthorBinding.inflate(inflater, container, false).also {
        binding = it

    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        val factory = AuthorsViewModelFactory(AuthorAPIService.getAuthorApiService())
        viewModel = ViewModelProvider(this, factory).get(AuthorsViewModel::class.java)
        val authorAdapter = AuthorListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = authorAdapter
        lifecycleScope.launch {
            viewModel.authorListData.collectLatest { pagedData ->
                authorAdapter.submitData(pagedData)
            }
        }

    }

}
