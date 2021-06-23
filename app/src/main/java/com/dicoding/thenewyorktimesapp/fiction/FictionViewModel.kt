package com.dicoding.thenewyorktimesapp.fiction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.thenewyorktimesapp.core.domain.usecase.BookUseCase

class FictionViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val fiction = bookUseCase.getAllFiction().asLiveData()
}