package com.task.quotable.model

data class QuoteData(val results: List<Result>)

data class Result(val content:String)