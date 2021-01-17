package com.task.quotable.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.QuoteResult
import kotlinx.android.synthetic.main.quote_row.view.*

class QuoteListAdapter : PagingDataAdapter<QuoteResult, QuoteListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.itemView.txt_quotes.text = getItem(position)?.content
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.quote_row, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<QuoteResult>() {

        override fun areItemsTheSame(oldItem: QuoteResult, newItem: QuoteResult): Boolean {
            return oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: QuoteResult, newItem: QuoteResult): Boolean {
            return oldItem == newItem
        }
    }

}