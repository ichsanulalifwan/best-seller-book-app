package com.dicoding.thenewyorktimespp.core.data.source.remote

import android.util.Log
import com.dicoding.thenewyorktimespp.BuildConfig
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiService
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionItem
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    suspend fun getAllFiction(): Flow<ApiResponse<List<FictionItem>>> {

        //get data fiction from remote api
        return flow {
            try {
                val response = apiService.getFiction(API_KEY)

                val dataArray = response.results.books

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllNonfiction(): Flow<ApiResponse<List<NonfictionItem>>> {

        // get data nonfiction from remote api
        return flow {
            try {
                val response = apiService.getNonfiction(API_KEY)

                val dataArray = response.results.books

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

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