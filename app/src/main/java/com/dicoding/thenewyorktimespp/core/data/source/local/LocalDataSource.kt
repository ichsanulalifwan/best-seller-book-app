package com.dicoding.thenewyorktimespp.core.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.NonfictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.local.room.BookDao

class LocalDataSource private constructor(private val bookDao: BookDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(bookDao: BookDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(bookDao)
            }
    }

    fun getAllFiction(): LiveData<List<FictionEntity>> = bookDao.getAllFiction()

    fun getFavoriteFiction(): LiveData<List<FictionEntity>> = bookDao.getFavoriteFiction()

    fun insertFiction(fiction: List<FictionEntity>) = bookDao.insertFiction(fiction)

    fun setFavoriteFiction(fiction: FictionEntity, newState: Boolean) {
        fiction.isFavorite = newState
        bookDao.updateFavoriteFiction(fiction)
    }

    fun getAllNonfiction(): LiveData<List<NonfictionEntity>> = bookDao.getAllNonfiction()

    fun getFavoriteNonfiction(): LiveData<List<NonfictionEntity>> = bookDao.getFavoriteNonfiction()

    fun insertNonfiction(nonfiction: List<NonfictionEntity>) = bookDao.insertNonfiction(nonfiction)

    fun setFavoriteNonfiction(nonfiction: NonfictionEntity, newState: Boolean) {
        nonfiction.isFavorite = newState
        bookDao.updateFavoriteNonfiction(nonfiction)
    }
}