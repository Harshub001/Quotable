package com.task.quotable.byrxjava.authorsbyrx.authoruirx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.quotable.databinding.FragmentAuthorBinding
import com.task.quotable.bycouroutines.adapter.AuthorsListAdapter
import com.task.quotable.byrxjava.authorsbyrx.authordatarx.AuthorInjectionRx
import com.task.quotable.bycouroutines.adapter.footeradapter.NetworkLoadStateAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable

class AuthorsFragmentRx: Fragment() {

    private val rxAuthorsDisposable = CompositeDisposable()

    private lateinit var rxAuthorsBinding: FragmentAuthorBinding
    private lateinit var rxAuthorsViewModel: AuthorViewModelRx
    private lateinit var rxAuthorsAdapter: AuthorsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        rxAuthorsBinding = FragmentAuthorBinding.inflate(inflater, container, false)

        val view = rxAuthorsBinding.root

        setupRecyclerView()

        rxAuthorsViewModel = ViewModelProvider(this, AuthorInjectionRx.provideRxViewModel(view.context))
                .get(AuthorViewModelRx::class.java)

        rxAuthorsDisposable.add(rxAuthorsViewModel.getRxAuthors().subscribe {
            rxAuthorsAdapter.submitData(lifecycle, it)
        })

        setupProgressBar()
        setupRetryButton()

        return view
    }

    private fun setupRetryButton() {
        rxAuthorsBinding.authorBtnRetry.setOnClickListener {
            rxAuthorsAdapter.retry()}
    }
    private fun setupRecyclerView() {
        rxAuthorsAdapter = AuthorsListAdapter()
        rxAuthorsBinding.authorRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        rxAuthorsBinding.authorRecyclerView.setHasFixedSize(true)
        rxAuthorsBinding.authorRecyclerView.adapter = rxAuthorsAdapter.withLoadStateFooter(
                footer = NetworkLoadStateAdapter { rxAuthorsAdapter.retry() }
        )
    }

    private fun setupProgressBar() {
        rxAuthorsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                rxAuthorsBinding.authorBtnRetry.visibility = View.GONE
                rxAuthorsBinding.authorProgressBar.visibility = View.VISIBLE
            } else {
                rxAuthorsBinding.authorProgressBar.visibility = View.GONE

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        rxAuthorsBinding.authorBtnRetry.visibility = View.VISIBLE
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
        rxAuthorsDisposable.dispose()
        super.onDestroyView()
    }

}
