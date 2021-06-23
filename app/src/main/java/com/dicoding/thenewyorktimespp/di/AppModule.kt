package com.dicoding.thenewyorktimespp.di

import com.dicoding.thenewyorktimespp.core.domain.usecase.BookInteractor
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase
import com.dicoding.thenewyorktimespp.detail.DetailBookViewModel
import com.dicoding.thenewyorktimespp.favorite.FavoriteListViewModel
import com.dicoding.thenewyorktimespp.fiction.FictionViewModel
import com.dicoding.thenewyorktimespp.nonfiction.NonfictionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BookUseCase> { BookInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FictionViewModel(get()) }
    viewModel { NonfictionViewModel(get()) }
    viewModel { DetailBookViewModel(get()) }
    viewModel { FavoriteListViewModel(get()) }
}