package com.task.quotable.byrxjava.quotesbyrx.quotedatarx

import com.task.quotable.data.QuoteData
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

     interface QuoteApiServiceRx {

        @GET("quotes")
        fun getQuotesListRx(@Query("page") page:Int): Single<QuoteData>

        companion object {
            private const val BASE_URL = "https://api.quotable.io/"

            fun getQuoteApiServiceRx(): QuoteApiServiceRx {
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC

                val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(QuoteApiServiceRx::class.java)
            }
        }
     }
