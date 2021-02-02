package com.task.quotable.byrxjava.quotesbyrx.quotedatarx


import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.byrxjava.quotesbyrx.quoteuirx.QuoteViewModelFactoryRx
import com.task.quotable.byrxjava.quotesbyrx.quotesreporx.QuoteRepositoryImplRx

object QuoteInjectionRx {
    fun provideRxViewModel(context: Context): ViewModelProvider.Factory {
        val pagingSource =
            QuoteDataSourceRx(
                quoteApiServiceRx = QuoteApiServiceRx.getQuoteApiServiceRx()
            )

        val repository =
            QuoteRepositoryImplRx(
                pagingSourceRx = pagingSource
            )

        return QuoteViewModelFactoryRx(
            repository
        )
    }
}