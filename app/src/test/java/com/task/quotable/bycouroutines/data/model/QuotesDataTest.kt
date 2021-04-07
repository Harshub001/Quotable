package com.task.quotable.bycouroutines.data.model

import com.task.quotable.data.QuoteData
import com.task.quotable.data.QuoteResult
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QuotesDataTest {

    lateinit var quoteData: QuoteData
    lateinit var quoteResult1: QuoteResult
    lateinit var quoteResult2: QuoteResult

    @BeforeEach
    fun setUp(){
        quoteResult1 = QuoteResult("content1")
        quoteResult2 = QuoteResult("content2")
        quoteData = QuoteData(1, listOf(quoteResult1, quoteResult2))
    }

    @Test
    @Throws(Exception::class)
    fun quote_result_should_not_null() {
        assertNotNull(quoteResult1)
        assertNotNull(quoteResult2)
    }

    @Test
    @Throws(Exception::class)
    fun quote_result_should_give_results() {
        assertEquals("content1", quoteResult1.content)
        assertEquals("content2", quoteResult2.content)
    }

    @Test
    @Throws(Exception::class)
    fun quote_data_should_not_null() {
        assertNotNull(quoteData)
    }

    @Test
    @Throws(Exception::class)
    fun quote_data_should_give_results(){
        assertEquals(1, quoteData.count)
        assertEquals(listOf(quoteResult1, quoteResult2), quoteData.results)
    }

}