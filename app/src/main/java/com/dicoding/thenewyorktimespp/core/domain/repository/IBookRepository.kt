package com.dicoding.thenewyorktimespp.core.domain.repository

import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface IBookRepository {

    fun getAllFiction(): Flow<Resource<List<Book>>>

    fun getFavoriteFiction(): Flow<List<Book>>

    fun setFavoriteFiction(fiction: Book, state: Boolean)

    fun getAllNonfiction(): Flow<Resource<List<Book>>>

    fun getFavoriteNonfiction(): Flow<List<Book>>

    fun setFavoriteNonfiction(nonfiction: Book, state: Boolean)
}