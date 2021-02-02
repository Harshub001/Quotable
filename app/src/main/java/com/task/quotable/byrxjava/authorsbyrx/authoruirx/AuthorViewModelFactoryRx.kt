package com.task.quotable.byrxjava.authorsbyrx.authoruirx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.byrxjava.authorsbyrx.authorsrepositoryrx.AuthorRepositoryRx

class AuthorViewModelFactoryRx(private val authorRepositoryRx: AuthorRepositoryRx) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorViewModelRx::class.java)) {
            return AuthorViewModelRx(
                    authorRepositoryRx
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}