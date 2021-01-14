package com.task.quotable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.task.quotable.datasource.PostDataSource
import com.task.quotable.service.APIService

class QuoteViewModel(private val apiService: APIService) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 20)) {
        PostDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}