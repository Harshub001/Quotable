package com.task.quotable.byrxjava.authorsbyrx.authorsrepositoryrx

import androidx.paging.PagingData
import com.task.quotable.data.datasource.AuthorResult
import io.reactivex.rxjava3.core.Flowable

interface AuthorRepositoryRx {
    fun getAuthorsRx(): Flowable<PagingData<AuthorResult>>
}