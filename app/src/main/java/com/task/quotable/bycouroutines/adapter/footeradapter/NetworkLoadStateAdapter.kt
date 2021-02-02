package com.task.quotable.bycouroutines.adapter.footeradapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import kotlinx.android.synthetic.main.network_state_footer_row.view.*

class NetworkLoadStateAdapter(
        private val retry: () -> Unit
 ) : LoadStateAdapter<NetworkLoadStateAdapter.LoadStateViewHolder>() {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progressBar = holder.itemView.load_state_progress
        val btnRetry = holder.itemView.load_state_retry
        val txtErrorMessage = holder.itemView.load_state_errorMessage

        btnRetry.isVisible = loadState !is LoadState.Loading
        txtErrorMessage.isVisible = loadState !is LoadState.Loading
        progressBar.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            txtErrorMessage.text = "NO INTERNET CONNECTION"
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.network_state_footer_row, parent, false)
        )
    }

    class LoadStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
}