package com.dicoding.thenewyorktimespp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase

class FavoriteListViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val favoriteFiction = bookUseCase.getFavoriteFiction().asLiveData()
    val favoriteNonfiction = bookUseCase.getFavoriteNonfiction().asLiveData()
}