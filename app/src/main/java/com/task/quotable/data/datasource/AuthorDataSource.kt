package com.task.quotable.data.datasource

import android.view.View
import android.widget.RelativeLayout
import androidx.paging.PagingSource
import com.task.quotable.service.AuthorAPIService

class AuthorDataSource(private val apiService: AuthorAPIService) : PagingSource<Int, AuthorResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AuthorResult> {
        try {

            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(currentLoadingPageKey)
            val responseData = mutableListOf<AuthorResult>()
            val data = response.body()?.results?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            return LoadResult.Page(
                    data = responseData,
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}