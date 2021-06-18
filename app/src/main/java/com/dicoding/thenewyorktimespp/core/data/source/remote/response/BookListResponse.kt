package com.dicoding.thenewyorktimespp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BookListResponse(

	@field:SerializedName("results")
	val results: Results,
)

data class Results(

	@field:SerializedName("books")
	val books: List<BooksItem>
)