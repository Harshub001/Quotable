package com.task.quotable.byrxjava.quotesbyrx.quotesreporx

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.task.quotable.data.QuoteResult
import com.task.quotable.byrxjava.quotesbyrx.quotedatarx.QuoteDataSourceRx
import io.reactivex.rxjava3.core.Flowable

class QuoteRepositoryImplRx(private val pagingSourceRx: QuoteDataSourceRx) : QuoteRepositoryRx {

    override fun getQuotesRx(): Flowable<PagingData<QuoteResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { pagingSourceRx }
        ).flowable
    }
}