package com.dicoding.thenewyorktimespp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.thenewyorktimespp.core.data.source.local.LocalDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.RemoteDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionItem
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionItem
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository
import com.dicoding.thenewyorktimespp.core.utils.AppExecutors
import com.dicoding.thenewyorktimespp.core.utils.DataMapper

class BookRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    override fun getAllFiction(): LiveData<Resource<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<FictionItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Book>> {
                return Transformations.map(localDataSource.getAllFiction()) {
                    DataMapper.mapEntitiesToFictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()
                //true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<FictionItem>>> =
                remoteDataSource.getAllFiction()

            override fun saveCallResult(data: List<FictionItem>) {
                val fictionList = DataMapper.mapResponsesToFictionEntities(data)
                localDataSource.insertFiction(fictionList)
            }
        }.asLiveData()

    override fun getFavoriteFiction(): LiveData<List<Book>> {
        return Transformations.map(localDataSource.getFavoriteFiction()) {
            DataMapper.mapEntitiesToFictionDomain(it)
        }
    }

    override fun setFavoriteFiction(fiction: Book, state: Boolean) {
        val fictionEntity = DataMapper.mapDomainToFictionEntity(fiction)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFiction(fictionEntity, state) }
    }

    override fun getAllNonfiction(): LiveData<Resource<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<NonfictionItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Book>> {
                return Transformations.map(localDataSource.getAllNonfiction()) {
                    DataMapper.mapEntitiesToNonfictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()
                //true // ganti dengan true jika ingin selalu mengambil data dari internet*/

            override fun createCall(): LiveData<ApiResponse<List<NonfictionItem>>> =
                remoteDataSource.getAllNonfiction()

            override fun saveCallResult(data: List<NonfictionItem>) {
                val nonfictionList = DataMapper.mapResponsesToNonfictionEntities(data)
                localDataSource.insertNonfiction(nonfictionList)
            }
        }.asLiveData()

    override fun getFavoriteNonfiction(): LiveData<List<Book>> {
        return Transformations.map(localDataSource.getFavoriteNonfiction()) {
            DataMapper.mapEntitiesToNonfictionDomain(it)
        }
    }

    override fun setFavoriteNonfiction(nonfiction: Book, state: Boolean) {
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