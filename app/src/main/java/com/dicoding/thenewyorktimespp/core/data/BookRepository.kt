package com.dicoding.thenewyorktimespp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.thenewyorktimespp.core.data.source.local.LocalDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.RemoteDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.BooksItem
import com.dicoding.thenewyorktimespp.core.domain.model.Fiction
import com.dicoding.thenewyorktimespp.core.domain.model.Nonfiction
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository
import com.dicoding.thenewyorktimespp.core.utils.AppExecutors
import com.dicoding.thenewyorktimespp.core.utils.DataMapper

class BookRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    override fun getAllFiction(): LiveData<Resource<List<Fiction>>> =
        object : NetworkBoundResource<List<Fiction>, List<BooksItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Fiction>> {
                return Transformations.map(localDataSource.getAllFiction()) {
                    DataMapper.mapEntitiesToFictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Fiction>?): Boolean =
                //data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<BooksItem>>> =
                remoteDataSource.getAllFiction()

            override fun saveCallResult(data: List<BooksItem>) {
                val fictionList = DataMapper.mapResponsesToFictionEntities(data)
                localDataSource.insertFiction(fictionList)
            }
        }.asLiveData()

    override fun getFavoriteFiction(): LiveData<List<Fiction>> {
        return Transformations.map(localDataSource.getFavoriteFiction()) {
            DataMapper.mapEntitiesToFictionDomain(it)
        }
    }

    override fun setFavoriteFiction(fiction: Fiction, state: Boolean) {
        val fictionEntity = DataMapper.mapDomainToFictionEntity(fiction)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFiction(fictionEntity, state) }
    }

    override fun getAllNonfiction(): LiveData<Resource<List<Nonfiction>>> =
        object : NetworkBoundResource<List<Nonfiction>, List<BooksItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Nonfiction>> {
                return Transformations.map(localDataSource.getAllNonfiction()) {
                    DataMapper.mapEntitiesToNonfictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Nonfiction>?): Boolean =
                // data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<BooksItem>>> =
                remoteDataSource.getAllFiction()

            override fun saveCallResult(data: List<BooksItem>) {
                val nonfictionList = DataMapper.mapResponsesToNonfictionEntities(data)
                localDataSource.insertNonfiction(nonfictionList)
            }
        }.asLiveData()

    override fun getFavoriteNonfiction(): LiveData<List<Nonfiction>> {
        return Transformations.map(localDataSource.getFavoriteNonfiction()) {
            DataMapper.mapEntitiesToNonfictionDomain(it)
        }
    }

    override fun setFavoriteNonfiction(nonfiction: Nonfiction, state: Boolean) {
        val nonfictionEntity = DataMapper.mapDomainToNonfictionEntity(nonfiction)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteNonfiction(nonfictionEntity, state) }
    }

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(remoteData, localData, appExecutors)
            }
    }
}