package com.dicoding.thenewyorktimesapp.nonfiction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.thenewyorktimesapp.core.domain.usecase.BookUseCase

class NonfictionViewModel(bookUseCase: BookUseCase) : ViewModel() {
    val nonfiction = bookUseCase.getAllNonfiction().asLiveData()
}