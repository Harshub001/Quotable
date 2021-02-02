package com.task.quotable.bycouroutines.service

import com.squareup.moshi.Moshi
import com.task.quotable.data.QuoteData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPIService {

    @GET("quotes")
    suspend fun getQuoteListData(@Query("page") pageNumber: Int): Response<QuoteData>

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"
        private val moshi = Moshi.Builder()
            .add(com.squareup.moshi.KotlinJsonAdapterFactory())
            .build()

        fun getQuoteApiService(): QuotesAPIService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(QuotesAPIService::class.java)
    }
}