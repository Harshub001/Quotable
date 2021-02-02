package com.task.quotable.byrxjava.quotesbyrx.quoteuirx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.task.quotable.data.QuoteResult
import com.task.quotable.byrxjava.quotesbyrx.quotesreporx.QuoteRepositoryRx
import io.reactivex.rxjava3.core.Flowable


class QuoteViewModelRx(private val quoteRepositoryRx: QuoteRepositoryRx) : ViewModel() {
    fun getRxQuotes(): Flowable<PagingData<QuoteResult>> {
        return quoteRepositoryRx
            .getQuotesRx()
            .cachedIn(viewModelScope)
    }
}