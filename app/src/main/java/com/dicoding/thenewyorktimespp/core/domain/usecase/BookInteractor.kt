package com.dicoding.thenewyorktimespp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {

    override fun getAllFiction(): LiveData<Resource<List<Book>>> = bookRepository.getAllFiction()

    override fun getFavoriteFiction(): LiveData<List<Book>> = bookRepository.getFavoriteFiction()

    override fun setFavoriteFiction(fiction: Book, state: Boolean) =
        bookRepository.setFavoriteFiction(fiction, state)

    override fun getAllNonfiction(): LiveData<Resource<List<Book>>> =
        bookRepository.getAllNonfiction()

    override fun getFavoriteNonfiction(): LiveData<List<Book>> =
        bookRepository.getFavoriteNonfiction()

    override fun setFavoriteNonfiction(nonfiction: Book, state: Boolean) =
        bookRepository.setFavoriteNonfiction(nonfiction, state)
}