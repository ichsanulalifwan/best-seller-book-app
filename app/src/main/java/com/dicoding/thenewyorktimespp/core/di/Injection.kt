package com.dicoding.thenewyorktimespp.core.di

import android.content.Context
import com.dicoding.thenewyorktimespp.core.data.BookRepository
import com.dicoding.thenewyorktimespp.core.data.source.local.LocalDataSource
import com.dicoding.thenewyorktimespp.core.data.source.local.room.BookDatabase
import com.dicoding.thenewyorktimespp.core.data.source.remote.RemoteDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiConfig
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookInteractor
import com.dicoding.thenewyorktimespp.core.domain.usecase.BookUseCase
import com.dicoding.thenewyorktimespp.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IBookRepository {
        val database = BookDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.bookDao())
        val appExecutors = AppExecutors()

        return BookRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideBookUseCase(context: Context): BookUseCase {
        val repository = provideRepository(context)
        return BookInteractor(repository)
    }
}