package com.dicoding.thenewyorktimespp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BooksItem(

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("primary_isbn10")
    val primaryIsbn10: String,

    @field:SerializedName("primary_isbn13")
    val primaryIsbn13: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("contributor")
    val contributor: String,

    @field:SerializedName("amazon_product_url")
    val amazonProductUrl: String,

    @field:SerializedName("rank")
    val rank: Int,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("book_image")
    val bookImage: String,

    @field:SerializedName("publisher")
    val publisher: String,

    @field:SerializedName("rank_last_week")
    val rankLastWeek: Int
)