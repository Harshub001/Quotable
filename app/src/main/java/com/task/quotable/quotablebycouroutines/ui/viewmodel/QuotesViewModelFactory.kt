package com.task.quotable.quotablebycouroutines.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.quotablebycouroutines.service.QuotesAPIService

class QuotesViewModelFactory(private val quotesApiService: QuotesAPIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(quotesApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
