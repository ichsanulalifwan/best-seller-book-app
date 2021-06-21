package com.dicoding.thenewyorktimespp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.thenewyorktimespp.BuildConfig
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiService
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.BookListResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.BooksItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun getAllFiction(): LiveData<ApiResponse<List<BooksItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<BooksItem>>>()

        //get data fiction from remote api
        val client = apiService.getFiction(API_KEY)

        client.enqueue(object : Callback<BookListResponse> {
            override fun onResponse(
                call: Call<BookListResponse>,
                response: Response<BookListResponse>
            ) {
                val dataArray = response.body()?.results?.books
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e(TAG, t.message.toString())
            }
        })

        return resultData
    }

    fun getAllNonfiction(): LiveData<ApiResponse<List<BooksItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<BooksItem>>>()

        //get data nonfiction from remote api
        val client = apiService.getNonfiction(API_KEY)

        client.enqueue(object : Callback<BookListResponse> {
            override fun onResponse(
                call: Call<BookListResponse>,
                response: Response<BookListResponse>
            ) {
                val dataArray = response.body()?.results?.books
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<BookListResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e(TAG, t.message.toString())
            }
        })

        return resultData
    }
}