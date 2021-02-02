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
import com.task.quotable.bycouroutines.adapter.AuthorsListAdapter
import com.task.quotable.databinding.FragmentAuthorBinding
import com.task.quotable.bycouroutines.service.AuthorsAPIService
import com.task.quotable.bycouroutines.ui.viewmodel.AuthorsViewModel
import com.task.quotable.bycouroutines.ui.viewmodel.AuthorsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AuthorsFragment : Fragment() {

    private lateinit var authorsViewModel: AuthorsViewModel
    private lateinit var authorsAdapter: AuthorsListAdapter
    private lateinit var authorsBinding: FragmentAuthorBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = FragmentAuthorBinding.inflate(inflater, container, false).also {
        authorsBinding = it
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAuthorViewModel()
        setupAuthorList()
        setupAuthorView()
        setupProgressBar()
        authorsBinding.authorBtnRetry.setOnClickListener {
            authorsAdapter.retry()
        }
    }

    private fun setupAuthorView() {
        lifecycleScope.launch {
            authorsViewModel.authorListData.collectLatest { pagedData ->
                authorsAdapter.submitData(pagedData)
            }
        }
    }

    private fun setupAuthorList() {
        authorsAdapter = AuthorsListAdapter()
        authorsBinding.authorRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        authorsBinding.authorRecyclerView.setHasFixedSize(true)
        authorsBinding.authorRecyclerView.adapter = authorsAdapter
    }

    private fun setupAuthorViewModel() {
        val authorFactory = AuthorsViewModelFactory(AuthorsAPIService.getAuthorApiService())
        authorsViewModel = ViewModelProvider(this, authorFactory).get(AuthorsViewModel::class.java)
    }

    private fun setupProgressBar() {
        authorsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                authorsBinding.authorBtnRetry.visibility = View.GONE
                authorsBinding.authorProgressBar.visibility = View.VISIBLE
            } else {
                authorsBinding.authorProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        authorsBinding.authorBtnRetry.visibility = View.VISIBLE
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
