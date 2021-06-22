package com.dicoding.thenewyorktimespp.fiction

import androidx.lifecycle.ViewModel
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase

class FictionViewModel(bookUseCase: BookUseCase)  : ViewModel() {
    val fiction = bookUseCase.getAllFiction()
}