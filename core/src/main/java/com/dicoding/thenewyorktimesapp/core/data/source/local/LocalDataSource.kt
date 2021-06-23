package com.dicoding.thenewyorktimesapp.core.data.source.local

import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.NonfictionEntity
import com.dicoding.thenewyorktimesapp.core.data.source.local.room.BookDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val bookDao: BookDao) {

    fun getAllFiction(): Flow<List<FictionEntity>> = bookDao.getAllFiction()

    fun getFavoriteFiction(): Flow<List<FictionEntity>> = bookDao.getFavoriteFiction()

    suspend fun insertFiction(fiction: List<FictionEntity>) = bookDao.insertFiction(fiction)

    fun setFavoriteFiction(fiction: FictionEntity, newState: Boolean) {
        fiction.isFavorite = newState
        bookDao.updateFavoriteFiction(fiction)
    }

    fun getAllNonfiction(): Flow<List<NonfictionEntity>> = bookDao.getAllNonfiction()

    fun getFavoriteNonfiction(): Flow<List<NonfictionEntity>> = bookDao.getFavoriteNonfiction()

    suspend fun insertNonfiction(nonfiction: List<NonfictionEntity>) =
        bookDao.insertNonfiction(nonfiction)

    fun setFavoriteNonfiction(nonfiction: NonfictionEntity, newState: Boolean) {
        nonfiction.isFavorite = newState
        bookDao.updateFavoriteNonfiction(nonfiction)
    }
}