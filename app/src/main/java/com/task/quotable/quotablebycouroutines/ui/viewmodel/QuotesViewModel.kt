package com.task.quotable.quotablebycouroutines.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.quotable.quotablebycouroutines.data.datasource.QuotesDataSource
import com.task.quotable.quotablebycouroutines.service.QuotesAPIService

class QuotesViewModel(private val quotesApiService: QuotesAPIService) : ViewModel() {
    val quoteListData = Pager(PagingConfig(pageSize = 20)) {
        QuotesDataSource(quotesApiService)
    }.flow.cachedIn(viewModelScope)
}