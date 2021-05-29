package com.dicoding.thenewyorktimespp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BookListResponse(

	@field:SerializedName("results")
	val results: Results? = null,
)

data class Results(

	@field:SerializedName("books")
	val books: List<BooksItem?>? = null,
)

data class BooksItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("primary_isbn10")
	val primaryIsbn10: String? = null,

	@field:SerializedName("primary_isbn13")
	val primaryIsbn13: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("contributor")
	val contributor: String? = null,

	@field:SerializedName("amazon_product_url")
	val amazonProductUrl: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("book_image")
	val bookImage: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("rank_last_week")
	val rankLastWeek: Int? = null
)
