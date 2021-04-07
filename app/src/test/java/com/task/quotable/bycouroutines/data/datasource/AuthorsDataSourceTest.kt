package com.task.quotable.bycouroutines.data.datasource

import androidx.paging.PagingSource
import com.task.quotable.bycouroutines.service.AuthorsAPIService
import com.task.quotable.data.datasource.AuthorData
import com.task.quotable.data.datasource.AuthorResult
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
/*
internal class AuthorsDataSourceTest{


    lateinit var authorsAPIService : AuthorsAPIService
    lateinit var authorData: AuthorData
    lateinit var authorResult1: AuthorResult
    lateinit var authorResult2: AuthorResult

    @BeforeEach
    fun setUp() {
        authorResult1 = AuthorResult("bio1", "name1")
        authorResult2 = AuthorResult("bio2", "name2")
        authorData = AuthorData(1, listOf(authorResult1, authorResult2))
    }

    @Test
    fun pageKeyedSubredditPagingSource() = runBlockingTest {
        val pagingSource = AuthorsDataSource(authorsAPIService)
        assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(authorResult1, authorResult2),
                    prevKey = null,
                    nextKey = authorResult2.name
                ),
                actual = pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 2,
                        placeholdersEnabled = false
                    )
                ),
        )
    }



}*/