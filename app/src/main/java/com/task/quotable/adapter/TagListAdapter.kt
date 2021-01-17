package com.task.quotable.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.task.quotable.R
import com.task.quotable.data.datasource.TagsResult


class TagListAdapter(val tagsResult: ArrayList<TagsResult>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tags_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tagsResult.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(tagsResult[position])
    }
}

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val title: TextView = itemView.findViewById(R.id.txt_tags)

    fun bind(tags: TagsResult) {
        title.text = tags.name
    }

}
