package com.task.quotable.byrxjava.tagsbyrx

import com.task.quotable.data.datasource.TagsResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TagsApiServiceRx {

    @GET("tags")
    fun getTags(): Observable<List<TagsResult>>

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"
        fun tagsCreate(): TagsApiServiceRx {

            val tagsRetrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return tagsRetrofit.create(TagsApiServiceRx::class.java)

        }
    }
}