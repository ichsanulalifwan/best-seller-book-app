package com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction

import com.google.gson.annotations.SerializedName

data class FictionListResponse(

    @field:SerializedName("results")
    val results: FictionResults,
)