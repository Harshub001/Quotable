package com.task.quotable.data.datasource

import com.google.gson.annotations.SerializedName

class TagsData : ArrayList<TagsResult>()

data class TagsResult(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quoteCount")
    val quoteCount: Int,
    @SerializedName("__v")
    val v: Int
)


