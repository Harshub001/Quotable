package com.task.quotable.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.service.AuthorAPIService

class AuthorsViewModelFactory (private val authorapiService: AuthorAPIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorsViewModel::class.java)) {
            return AuthorsViewModel(authorapiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}