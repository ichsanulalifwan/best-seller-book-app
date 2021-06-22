package com.dicoding.thenewyorktimespp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Book

interface BookUseCase {

    fun getAllFiction(): LiveData<Resource<List<Book>>>

    fun getFavoriteFiction(): LiveData<List<Book>>

    fun setFavoriteFiction(fiction: Book, state: Boolean)

    fun getAllNonfiction(): LiveData<Resource<List<Book>>>

    fun getFavoriteNonfiction(): LiveData<List<Book>>

    fun setFavoriteNonfiction(nonfiction: Book, state: Boolean)
}