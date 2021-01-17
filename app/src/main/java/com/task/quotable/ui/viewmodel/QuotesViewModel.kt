package com.task.quotable.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.quotable.data.datasource.QuoteDataSource
import com.task.quotable.service.QuotesAPIService

class QuotesViewModel(private val quotesApiService: QuotesAPIService) : ViewModel() {
    val quoteListData = Pager(PagingConfig(pageSize = 20)) {
        QuoteDataSource(quotesApiService)
    }.flow.cachedIn(viewModelScope)
}