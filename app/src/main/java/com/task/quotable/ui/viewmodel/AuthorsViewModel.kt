package com.task.quotable.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.quotable.data.datasource.AuthorDataSource
import com.task.quotable.service.AuthorAPIService

class AuthorsViewModel(private val authorApiService: AuthorAPIService) : ViewModel() {
    val authorListData = Pager(PagingConfig(pageSize = 20)) {
        AuthorDataSource(authorApiService)
    }.flow.cachedIn(viewModelScope)
}