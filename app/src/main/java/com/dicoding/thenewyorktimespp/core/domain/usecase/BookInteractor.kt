package com.dicoding.thenewyorktimespp.core.domain.usecase

import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository
import kotlinx.coroutines.flow.Flow

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {

    override fun getAllFiction(): Flow<Resource<List<Book>>> = bookRepository.getAllFiction()

    override fun getFavoriteFiction(): Flow<List<Book>> = bookRepository.getFavoriteFiction()

    override fun setFavoriteFiction(fiction: Book, state: Boolean) =
        bookRepository.setFavoriteFiction(fiction, state)

    override fun getAllNonfiction(): Flow<Resource<List<Book>>> =
        bookRepository.getAllNonfiction()

    override fun getFavoriteNonfiction(): Flow<List<Book>> =
        bookRepository.getFavoriteNonfiction()

    override fun setFavoriteNonfiction(nonfiction: Book, state: Boolean) =
        bookRepository.setFavoriteNonfiction(nonfiction, state)
}