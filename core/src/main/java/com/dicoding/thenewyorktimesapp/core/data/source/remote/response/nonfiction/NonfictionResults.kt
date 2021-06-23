package com.dicoding.thenewyorktimesapp.core.data.source.remote.response.nonfiction

import com.google.gson.annotations.SerializedName

data class NonfictionResults(

    @field:SerializedName("books")
    val books: List<NonfictionItem>
)
