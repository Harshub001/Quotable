package com.task.quotable.bycouroutines.data.model

import com.task.quotable.data.datasource.AuthorData
import com.task.quotable.data.datasource.AuthorResult
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthorsDataTest {

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
    @Throws(Exception::class)
    fun author_result_should_not_null() {
        assertNotNull(authorResult1)
        assertNotNull(authorResult2)
    }

    @Test
    @Throws(Exception::class)
    fun author_result_should_give_results() {
        assertEquals("bio1", authorResult1.bio)
        assertEquals("name1", authorResult1.name)
        assertEquals("bio2", authorResult2.bio)
        assertEquals("name2", authorResult2.name)
    }

    @Test
    @Throws(Exception::class)
    fun author_data_should_not_null() {
        assertNotNull(authorData)
    }

    @Test
    @Throws(Exception::class)
    fun author_data_should_give_results() {
        assertEquals(1, authorData.count)
        assertEquals(listOf(authorResult1, authorResult2), authorData.results)
    }

}