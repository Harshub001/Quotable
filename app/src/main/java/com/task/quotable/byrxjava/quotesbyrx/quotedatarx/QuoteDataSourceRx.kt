package com.task.quotable.byrxjava.quotesbyrx.quotedatarx

import androidx.paging.rxjava3.RxPagingSource
import com.task.quotable.data.QuoteData
import com.task.quotable.data.QuoteResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class QuoteDataSourceRx(var quoteApiServiceRx: QuoteApiServiceRx) : RxPagingSource<Int,QuoteResult>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, QuoteResult>> {
        var currentPageKey = params.key ?: 1

        return quoteApiServiceRx.getQuotesListRx(currentPageKey)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { toLoadResult(it,currentPageKey)}
            .onErrorReturn { LoadResult.Error(it)}
    }

    private fun toLoadResult(response: QuoteData, position: Int): LoadResult<Int, QuoteResult> {
        return LoadResult.Page(
            data = response.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey =  if(position == response.count) null else position+1
        )
    }
}