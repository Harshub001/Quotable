package com.task.quotable.quotablebycouroutines.service

import com.squareup.moshi.Moshi
import com.task.quotable.data.datasource.AuthorData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorsAPIService {

    @GET("authors")
    suspend fun getAuthorListData(@Query("page") pageNumber: Int): Response<AuthorData>

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"
        private val moshi = Moshi.Builder()
                .add(com.squareup.moshi.KotlinJsonAdapterFactory())
                .build()

        fun getAuthorApiService(): AuthorsAPIService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(AuthorsAPIService::class.java)
    }
}