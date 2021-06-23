package com.dicoding.thenewyorktimespp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase

class DetailBookViewModel(private val bookUseCase: BookUseCase) : ViewModel() {

    fun setFavoriteFiction(book: Book, newStatus: Boolean) =
        bookUseCase.setFavoriteFiction(book, newStatus)

    fun setFavoriteNonfiction(book: Book, newStatus: Boolean) =
        bookUseCase.setFavoriteNonfiction(book, newStatus)
}