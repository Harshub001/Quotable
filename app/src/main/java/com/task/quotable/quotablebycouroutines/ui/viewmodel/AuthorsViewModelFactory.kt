package com.task.quotable.quotablebycouroutines.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.quotablebycouroutines.service.AuthorsAPIService

class AuthorsViewModelFactory (private val authorApiService: AuthorsAPIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorsViewModel::class.java)) {
            return AuthorsViewModel(authorApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}