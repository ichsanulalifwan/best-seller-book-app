package com.dicoding.thenewyorktimesapp.core.data.source.remote.response.nonfiction

import com.google.gson.annotations.SerializedName

data class NonfictionListResponse(

    @field:SerializedName("results")
    val results: NonfictionResults,
)
