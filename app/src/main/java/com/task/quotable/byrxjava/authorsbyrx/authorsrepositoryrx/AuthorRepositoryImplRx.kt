package com.task.quotable.byrxjava.authorsbyrx.authorsrepositoryrx

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.task.quotable.data.datasource.AuthorResult
import com.task.quotable.byrxjava.authorsbyrx.authordatarx.AuthorDataSourceRx
import io.reactivex.rxjava3.core.Flowable

class AuthorRepositoryImplRx(private val pagingSourceRx: AuthorDataSourceRx) : AuthorRepositoryRx {

    override fun getAuthorsRx(): Flowable<PagingData<AuthorResult>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 20
                ),
                pagingSourceFactory = { pagingSourceRx }
        ).flowable
    }
}