package com.task.quotable.byrxjava.authorsbyrx.authordatarx

import com.task.quotable.data.datasource.AuthorData
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorApiServiceRx {
    @GET("authors")
    fun getAuthorsListRx(@Query("page") page:Int): Single<AuthorData>

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"

        fun getAuthorApiServiceRx(): AuthorApiServiceRx {
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
                    .create(AuthorApiServiceRx::class.java)
        }
    }
}