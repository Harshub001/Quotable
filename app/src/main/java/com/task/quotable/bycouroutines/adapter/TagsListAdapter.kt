package com.task.quotable.bycouroutines.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.datasource.TagsResult

class TagsListAdapter(private val tagsResult: ArrayList<TagsResult>): RecyclerView.Adapter<TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tags_row, parent, false)
        return TagsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tagsResult.size
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        return holder.bind(tagsResult[position])
     }
  }

    class TagsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val title: TextView = itemView.findViewById(R.id.txt_tags)

    fun bind(tags: TagsResult) {
        title.text = tags.name
     }

 }
