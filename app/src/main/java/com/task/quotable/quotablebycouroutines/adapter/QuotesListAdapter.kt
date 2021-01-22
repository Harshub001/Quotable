package com.task.quotable.quotablebycouroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.QuoteResult
import kotlinx.android.synthetic.main.quote_row.view.*

class QuotesListAdapter : PagingDataAdapter<QuoteResult, QuotesListAdapter.QuotesViewHolder>(
    DataDifferentiators
) {

    class QuotesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
       holder.itemView.txt_quotes.text = getItem(position)?.content

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.quote_row, parent, false)
        )
    }

    object DataDifferentiators : DiffUtil.ItemCallback<QuoteResult>() {
        override fun areItemsTheSame(oldItem: QuoteResult, newItem: QuoteResult): Boolean {
            return oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: QuoteResult, newItem: QuoteResult): Boolean {
            return oldItem == newItem
        }
    }
}