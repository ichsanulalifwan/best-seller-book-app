package com.dicoding.thenewyorktimesapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.NonfictionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    // Fiction
    @Query("SELECT * FROM fiction")
    fun getAllFiction(): Flow<List<FictionEntity>>

    @Query("SELECT * FROM fiction where isFavorite = 1")
    fun getFavoriteFiction(): Flow<List<FictionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiction(fiction: List<FictionEntity>)

    @Update
    fun updateFavoriteFiction(fiction: FictionEntity)

    // Nonfiction
    @Query("SELECT * FROM nonfiction")
    fun getAllNonfiction(): Flow<List<NonfictionEntity>>

    @Query("SELECT * FROM nonfiction where isFavorite = 1")
    fun getFavoriteNonfiction(): Flow<List<NonfictionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNonfiction(nonfiction: List<NonfictionEntity>)

    @Update
    fun updateFavoriteNonfiction(nonfiction: NonfictionEntity)
}