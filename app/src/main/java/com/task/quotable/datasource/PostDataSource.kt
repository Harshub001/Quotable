package com.task.quotable.datasource

import androidx.paging.PagingSource
import com.task.quotable.model.Result
import com.task.quotable.service.APIService

class PostDataSource(private val apiService: APIService) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getListData(currentLoadingPageKey)
            val responseData = mutableListOf<Result>()
            val data = response.body()?.results ?: emptyList()
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