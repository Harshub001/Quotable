package com.task.quotable.byrxjava.authorsbyrx.authordatarx

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.task.quotable.byrxjava.authorsbyrx.authorsrepositoryrx.AuthorRepositoryImplRx
import com.task.quotable.byrxjava.authorsbyrx.authoruirx.AuthorViewModelFactoryRx

object AuthorInjectionRx {
    fun provideRxViewModel(context: Context): ViewModelProvider.Factory {
        val pagingSource =
                AuthorDataSourceRx(
                        authorApiServiceRx = AuthorApiServiceRx.getAuthorApiServiceRx()
                )

        val repository =
                AuthorRepositoryImplRx(
                        pagingSourceRx = pagingSource
                )

        return AuthorViewModelFactoryRx(
                repository
        )
    }
}