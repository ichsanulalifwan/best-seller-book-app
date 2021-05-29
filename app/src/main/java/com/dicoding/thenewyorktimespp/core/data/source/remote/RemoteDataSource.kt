package com.dicoding.thenewyorktimespp.core.data.source.remote

import com.dicoding.thenewyorktimespp.BuildConfig
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiService

class RemoteDataSource private constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        private const val API_KEY = BuildConfig.API_KEY
        private const val TAG = "RemoteDataSource"

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}