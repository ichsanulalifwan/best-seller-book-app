package com.dicoding.thenewyorktimesapp.core.domain.usecase

import com.dicoding.thenewyorktimesapp.core.data.Resource
import com.dicoding.thenewyorktimesapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookUseCase {

    fun getAllFiction(): Flow<Resource<List<Book>>>

    fun getFavoriteFiction(): Flow<List<Book>>

    fun setFavoriteFiction(fiction: Book, state: Boolean)

    fun getAllNonfiction(): Flow<Resource<List<Book>>>

    fun getFavoriteNonfiction(): Flow<List<Book>>

    fun setFavoriteNonfiction(nonfiction: Book, state: Boolean)
}