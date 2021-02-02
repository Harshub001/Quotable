package com.task.quotable.byrxjava.quotesbyrx.quoteuirx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.byrxjava.quotesbyrx.quotesreporx.QuoteRepositoryRx

class QuoteViewModelFactoryRx(private val quoteRepositoryRx: QuoteRepositoryRx) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModelRx::class.java)) {
            return QuoteViewModelRx(
                quoteRepositoryRx
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}