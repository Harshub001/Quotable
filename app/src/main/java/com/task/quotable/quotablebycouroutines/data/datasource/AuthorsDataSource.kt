package com.task.quotable.quotablebycouroutines.data.datasource

import androidx.paging.PagingSource
import com.task.quotable.data.datasource.AuthorResult
import com.task.quotable.quotablebycouroutines.service.AuthorsAPIService

class AuthorsDataSource(private val authorsApiService: AuthorsAPIService) : PagingSource<Int, AuthorResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AuthorResult> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val authorResponse = authorsApiService.getAuthorListData(currentLoadingPageKey)
            val authorResponseData = mutableListOf<AuthorResult>()
            val authorData = authorResponse.body()?.results ?: emptyList()
            authorResponseData.addAll(authorData)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            LoadResult.Page(
                    data = authorResponseData,
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}