package com.task.quotable.bycouroutines.data.datasource

import androidx.paging.PagingSource
import com.task.quotable.data.QuoteResult
import com.task.quotable.bycouroutines.service.QuotesAPIService


class QuotesDataSource(private val quotesApiService: QuotesAPIService) : PagingSource<Int, QuoteResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteResult> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val quoteResponse = quotesApiService.getQuoteListData(currentLoadingPageKey)
            val quoteResponseData = mutableListOf<QuoteResult>()
            val quoteData = quoteResponse.body()?.results ?: emptyList()
            quoteResponseData.addAll(quoteData)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            LoadResult.Page(
                    data = quoteResponseData,
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}