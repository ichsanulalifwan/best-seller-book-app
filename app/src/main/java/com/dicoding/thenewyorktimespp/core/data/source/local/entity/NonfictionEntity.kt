package com.dicoding.thenewyorktimespp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nonfiction")
data class NonfictionEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "primary_isbn10")
    var primaryIsbn10: String,

    @ColumnInfo(name = "primary_isbn13")
    val primaryIsbn13: String,

    @ColumnInfo(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "rank_last_week")
    val rankLastWeek: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "contributor")
    val contributor: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "book_image")
    val bookImage: String,

    @ColumnInfo(name = "amazon_product_url")
    val amazonProductUrl: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
