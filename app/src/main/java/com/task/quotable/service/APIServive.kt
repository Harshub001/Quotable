package com.task.quotable.service

import com.squareup.moshi.Moshi
import com.task.quotable.model.QuoteData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("quotes")
    suspend fun getListData(@Query("page") pageNumber: Int): Response<QuoteData>

    companion object {

        private val moshi = Moshi.Builder()
            .add(com.squareup.moshi.KotlinJsonAdapterFactory())
            .build()

        fun getApiService() = Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(APIService::class.java)
    }
}