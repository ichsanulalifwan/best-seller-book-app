package com.dicoding.thenewyorktimespp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Fiction
import com.dicoding.thenewyorktimespp.core.domain.model.Nonfiction
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository

class BookInteractor(private val bookRepository: IBookRepository) : BookUseCase {

    override fun getAllFiction(): LiveData<Resource<List<Fiction>>> = bookRepository.getAllFiction()

    override fun getFavoriteFiction(): LiveData<List<Fiction>> = bookRepository.getFavoriteFiction()

    override fun setFavoriteFiction(fiction: Fiction, state: Boolean) =
        bookRepository.setFavoriteFiction(fiction, state)

    override fun getAllNonfiction(): LiveData<Resource<List<Nonfiction>>> =
        bookRepository.getAllNonfiction()

    override fun getFavoriteNonfiction(): LiveData<List<Nonfiction>> =
        bookRepository.getFavoriteNonfiction()

    override fun setFavoriteNonfiction(nonfiction: Nonfiction, state: Boolean) =
        bookRepository.setFavoriteNonfiction(nonfiction, state)
}