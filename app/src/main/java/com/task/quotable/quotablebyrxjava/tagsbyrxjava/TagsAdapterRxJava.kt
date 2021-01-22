package com.task.quotable.quotablebyrxjava.tagsbyrxjava

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.datasource.TagsResult
import kotlinx.android.synthetic.main.tags_row.view.*

class TagsAdapterRxJava(private val tagsList: List<TagsResult>) : RecyclerView.Adapter<TagsAdapterRxJava.TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TagsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tags_row, parent, false))

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) = holder.bind(tagsList[position])

    override fun getItemCount() = tagsList.size

    class TagsViewHolder(TagsView: View) : RecyclerView.ViewHolder(TagsView) {

        fun bind(tagsResult: TagsResult) = with(itemView) {
            txt_tags.text = tagsResult.name
           }
        }
    }
