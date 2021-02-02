package com.task.quotable.data

data class QuoteData(val count: Int,val results: List<QuoteResult>)

data class QuoteResult(val content: String)
