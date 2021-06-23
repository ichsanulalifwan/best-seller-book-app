package com.dicoding.thenewyorktimespp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.NonfictionEntity

@Database(
    entities = [FictionEntity::class, NonfictionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}