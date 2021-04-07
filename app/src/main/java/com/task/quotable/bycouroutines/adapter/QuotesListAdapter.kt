package com.task.quotable.bycouroutines.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.itemView.txt_quotes.text = getItem(position)?.content
        holder.itemView.txt_quotes_author.text = "~ by " + getItem(position)?.author
        val isExpandable: Boolean = getItem(position)?.expandable == true
            holder.itemView.txt_quotes_expand.visibility = if(isExpandable) View.VISIBLE else View.GONE
            holder.itemView.txt_quotes.visibility = if(isExpandable) View.GONE else View.VISIBLE
            holder.itemView.txt_quotes_expand.text = getItem(position)?.content
            holder.itemView.quotes_txt_cardView.setOnClickListener {
                getItem(position)?.expandable = !(getItem(position)?.expandable)!!
                notifyItemChanged(position)
            }
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