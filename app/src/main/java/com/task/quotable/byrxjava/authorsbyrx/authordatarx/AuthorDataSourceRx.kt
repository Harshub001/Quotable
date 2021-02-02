package com.task.quotable.byrxjava.authorsbyrx.authordatarx

import androidx.paging.rxjava3.RxPagingSource
import com.task.quotable.data.datasource.AuthorData
import com.task.quotable.data.datasource.AuthorResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthorDataSourceRx(var authorApiServiceRx: AuthorApiServiceRx) : RxPagingSource<Int, AuthorResult>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, AuthorResult>> {
        var currentPageKey = params.key ?: 1

        return authorApiServiceRx.getAuthorsListRx(currentPageKey)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { toLoadResult(it,currentPageKey)}
                .onErrorReturn { LoadResult.Error(it)}
    }

    private fun toLoadResult(response: AuthorData, position: Int): LoadResult<Int, AuthorResult> {
        return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey =  if(position == response.count) null else position+1
        )
    }
}