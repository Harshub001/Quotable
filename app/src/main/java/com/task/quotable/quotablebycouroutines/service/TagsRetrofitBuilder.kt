package com.task.quotable.quotablebycouroutines.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TagsRetrofitBuilder {

    private val tagsClient = OkHttpClient.Builder().build()
    private const val BASE_URL = "https://api.quotable.io/"
    private val tagsRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(tagsClient)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return tagsRetrofit.create(service)
    }

}