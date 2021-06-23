package com.dicoding.thenewyorktimesapp.di

import com.dicoding.thenewyorktimesapp.core.domain.usecase.BookInteractor
import com.dicoding.thenewyorktimesapp.core.domain.usecase.BookUseCase
import com.dicoding.thenewyorktimesapp.detail.DetailBookViewModel
import com.dicoding.thenewyorktimesapp.fiction.FictionViewModel
import com.dicoding.thenewyorktimesapp.nonfiction.NonfictionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BookUseCase> { BookInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FictionViewModel(get()) }
    viewModel { NonfictionViewModel(get()) }
    viewModel { DetailBookViewModel(get()) }
}