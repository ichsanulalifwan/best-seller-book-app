package com.dicoding.thenewyorktimesapp.core.data.source.remote.response.fiction

import com.google.gson.annotations.SerializedName

data class FictionResults(

    @field:SerializedName("books")
    val books: List<FictionItem>
)
