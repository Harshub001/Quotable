package com.task.quotable.byrxjava.quotesbyrx.quotesreporx

import androidx.paging.PagingData
import com.task.quotable.data.QuoteResult
import io.reactivex.rxjava3.core.Flowable

interface QuoteRepositoryRx {
    fun getQuotesRx(): Flowable<PagingData<QuoteResult>>
}