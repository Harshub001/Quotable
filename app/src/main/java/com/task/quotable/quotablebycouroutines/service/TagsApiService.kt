package com.task.quotable.quotablebycouroutines.service



import com.task.quotable.data.datasource.TagsResult
import retrofit2.Call
import retrofit2.http.GET

interface TagsApiService {

    @GET("tags")
    fun getTags(): Call<ArrayList<TagsResult>>

}