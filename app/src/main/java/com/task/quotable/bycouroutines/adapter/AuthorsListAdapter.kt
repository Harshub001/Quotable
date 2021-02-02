package com.task.quotable.bycouroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.datasource.AuthorResult
import kotlinx.android.synthetic.main.author_row.view.*

class AuthorsListAdapter : PagingDataAdapter<AuthorResult, AuthorsListAdapter.AuthorsViewHolder>(
    DataDifferentiators
) {

    class AuthorsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: AuthorsViewHolder, position: Int) {
        holder.itemView.txt_author_name.text = getItem(position)?.name
        holder.itemView.txt_author_bio.text = getItem(position)?.bio
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsViewHolder {
        return AuthorsViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.author_row, parent, false)
        )
    }

    object DataDifferentiators : DiffUtil.ItemCallback<AuthorResult>() {
        override fun areItemsTheSame(oldItem: AuthorResult, newItem: AuthorResult): Boolean {
            return oldItem.bio == newItem.bio
        }

        override fun areContentsTheSame(oldItem: AuthorResult, newItem: AuthorResult): Boolean {
            return oldItem == newItem
        }
    }

}