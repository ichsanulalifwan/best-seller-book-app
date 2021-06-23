package com.dicoding.thenewyorktimespp.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase

class FavoriteListViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val favoriteFiction = bookUseCase.getFavoriteFiction()
    val favoriteNonfiction = bookUseCase.getFavoriteNonfiction()
}