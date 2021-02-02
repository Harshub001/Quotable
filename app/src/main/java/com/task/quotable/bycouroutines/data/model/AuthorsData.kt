package com.task.quotable.data.datasource



data class AuthorData (val count: Int, val results: List<AuthorResult>)

data class AuthorResult(val bio: String, val name: String)