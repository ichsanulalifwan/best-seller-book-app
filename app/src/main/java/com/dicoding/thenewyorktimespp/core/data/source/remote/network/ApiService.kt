package com.dicoding.thenewyorktimespp.core.data.source.remote.network

import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionListResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("Combined Print and E-Book Fiction.json")
    suspend fun getFiction(
        @Query("api-key") apiKey: String
    ): FictionListResponse

    @GET("Combined Print and E-Book Nonfiction.json")
    suspend fun getNonfiction(
        @Query("api-key") apiKey: String
    ): NonfictionListResponse
}