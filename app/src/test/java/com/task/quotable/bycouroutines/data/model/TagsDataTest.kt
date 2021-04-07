package com.task.quotable.bycouroutines.data.model

import com.task.quotable.data.datasource.TagsResult
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TagsDataTest{

    private lateinit var tagsResult: TagsResult

    @BeforeEach
    fun setUp(){
        tagsResult = TagsResult(
            "1",
            "name",
            20,
            2
        )
    }

    @Test
    @Throws(Exception::class)
    fun tags_result_should_not_null() {
        assertNotNull(tagsResult)
    }

    @Test
    @Throws(Exception::class)
    fun tags_result_should_give_results() {
        assertEquals("1", tagsResult.id)
        assertEquals("name", tagsResult.name)
        assertEquals(20, tagsResult.quoteCount)
        assertEquals(2, tagsResult.v)
    }

}