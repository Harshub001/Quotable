package com.task.quotable.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.datasource.AuthorResult
import kotlinx.android.synthetic.main.author_row.view.*

class AuthorListAdapter : PagingDataAdapter<AuthorResult, AuthorListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txt_author_name.text = getItem(position)?.name
        holder.itemView.txt_author_bio.text = getItem(position)?.bio
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.author_row, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<AuthorResult>() {

        override fun areItemsTheSame(oldItem: AuthorResult, newItem: AuthorResult): Boolean {
            return oldItem.bio == newItem.bio
        }

        override fun areContentsTheSame(oldItem: AuthorResult, newItem: AuthorResult): Boolean {
            return oldItem == newItem
        }
    }

}