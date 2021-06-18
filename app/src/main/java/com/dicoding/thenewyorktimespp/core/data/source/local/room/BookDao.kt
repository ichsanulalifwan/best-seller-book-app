package com.dicoding.thenewyorktimespp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.NonfictionEntity

@Dao
interface BookDao {

    // Fiction
    @Query("SELECT * FROM fiction")
    fun getAllFiction(): LiveData<List<FictionEntity>>

    @Query("SELECT * FROM fiction where isFavorite = 1")
    fun getFavoriteFiction(): LiveData<List<FictionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFiction(fiction: List<FictionEntity>)

    @Update
    fun updateFavoriteFiction(fiction: FictionEntity)

    // Nonfiction
    @Query("SELECT * FROM nonfiction")
    fun getAllNonfiction(): LiveData<List<NonfictionEntity>>

    @Query("SELECT * FROM nonfiction where isFavorite = 1")
    fun getFavoriteNonfiction(): LiveData<List<NonfictionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNonfiction(nonfiction: List<NonfictionEntity>)

    @Update
    fun updateFavoriteNonfiction(nonfiction: NonfictionEntity)
}