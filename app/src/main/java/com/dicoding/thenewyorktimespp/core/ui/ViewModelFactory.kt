package com.dicoding.thenewyorktimespp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.thenewyorktimespp.core.di.Injection
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase
import com.dicoding.thenewyorktimespp.detail.DetailBookViewModel
import com.dicoding.thenewyorktimespp.favorite.FavoriteViewModel
import com.dicoding.thenewyorktimespp.fiction.FictionViewModel
import com.dicoding.thenewyorktimespp.nonfiction.NonfictionViewModel

class ViewModelFactory private constructor(private val bookUseCase: BookUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FictionViewModel::class.java) -> {
                FictionViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(NonfictionViewModel::class.java) -> {
                NonfictionViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(bookUseCase) as T
            }
            modelClass.isAssignableFrom(DetailBookViewModel::class.java) -> {
                DetailBookViewModel(bookUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideBookUseCase(context)
                )
            }
    }
}