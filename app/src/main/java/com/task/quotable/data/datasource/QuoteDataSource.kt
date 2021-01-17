package com.task.quotable.data.datasource


import androidx.paging.PagingSource
import com.task.quotable.data.QuoteResult
import com.task.quotable.service.QuotesAPIService

class QuoteDataSource(private val quotesApiService: QuotesAPIService) : PagingSource<Int, QuoteResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteResult> {
        try {

            val currentLoadingPageKey = params.key ?: 1
            val response = quotesApiService.getListData(currentLoadingPageKey)
            val responseData = mutableListOf<QuoteResult>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}