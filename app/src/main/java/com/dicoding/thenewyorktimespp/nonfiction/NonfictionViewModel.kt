package com.dicoding.thenewyorktimespp.nonfiction

import androidx.lifecycle.ViewModel
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase

class NonfictionViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val nonfiction = bookUseCase.getAllNonfiction()
}