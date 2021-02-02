package com.task.quotable.byrxjava.authorsbyrx.authoruirx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.task.quotable.data.datasource.AuthorResult
import com.task.quotable.byrxjava.authorsbyrx.authorsrepositoryrx.AuthorRepositoryRx
import io.reactivex.rxjava3.core.Flowable

class AuthorViewModelRx(private val authorRepositoryRx: AuthorRepositoryRx) : ViewModel() {
    fun getRxAuthors(): Flowable<PagingData<AuthorResult>> {
        return authorRepositoryRx
                .getAuthorsRx()
                .cachedIn(viewModelScope)
    }
}