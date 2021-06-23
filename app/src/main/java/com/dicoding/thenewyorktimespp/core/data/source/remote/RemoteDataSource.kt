package com.dicoding.thenewyorktimespp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.thenewyorktimespp.BuildConfig
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiService
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionItem
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionListResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionItem
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    fun getAllFiction(): LiveData<ApiResponse<List<FictionItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<FictionItem>>>()

        //get data fiction from remote api
        val client = apiService.getFiction(API_KEY)

        client.enqueue(object : Callback<FictionListResponse> {
            override fun onResponse(
                call: Call<FictionListResponse>,
                response: Response<FictionListResponse>
            ) {
                val dataArray = response.body()?.results?.books
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<FictionListResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e(TAG, t.message.toString())
            }
        })

        return resultData
    }

    fun getAllNonfiction(): LiveData<ApiResponse<List<NonfictionItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<NonfictionItem>>>()

        // get data nonfiction from remote api
        val client = apiService.getNonfiction(API_KEY)

        client.enqueue(object : Callback<NonfictionListResponse> {
            override fun onResponse(
                call: Call<NonfictionListResponse>,
                response: Response<NonfictionListResponse>
            ) {
                val dataArray = response.body()?.results?.books
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<NonfictionListResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e(TAG, t.message.toString())
            }
        })

        return resultData
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