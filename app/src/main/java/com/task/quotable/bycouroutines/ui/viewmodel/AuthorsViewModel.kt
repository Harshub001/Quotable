package com.task.quotable.bycouroutines.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.task.quotable.bycouroutines.data.datasource.AuthorsDataSource
import com.task.quotable.bycouroutines.service.AuthorsAPIService

class AuthorsViewModel(private val authorsApiService: AuthorsAPIService) : ViewModel() {
    val authorListData = Pager(PagingConfig(pageSize = 20)) {
        AuthorsDataSource(authorsApiService)
    }.flow.cachedIn(viewModelScope)
}