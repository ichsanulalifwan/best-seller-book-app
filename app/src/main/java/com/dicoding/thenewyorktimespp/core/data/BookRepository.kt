package com.dicoding.thenewyorktimespp.core.data

import com.dicoding.thenewyorktimespp.core.data.source.local.LocalDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.RemoteDataSource
import com.dicoding.thenewyorktimespp.core.data.source.remote.network.ApiResponse
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.fiction.FictionItem
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.nonfiction.NonfictionItem
import com.dicoding.thenewyorktimespp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.core.domain.repository.IBookRepository
import com.dicoding.thenewyorktimespp.core.utils.AppExecutors
import com.dicoding.thenewyorktimespp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {

    override fun getAllFiction(): Flow<Resource<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<FictionItem>>() {
            override fun loadFromDB(): Flow<List<Book>> {
                return localDataSource.getAllFiction().map {
                    DataMapper.mapEntitiesToFictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<FictionItem>>> =
                remoteDataSource.getAllFiction()

            override suspend fun saveCallResult(data: List<FictionItem>) {
                val fictionList = DataMapper.mapResponsesToFictionEntities(data)
                localDataSource.insertFiction(fictionList)
            }
        }.asFlow()

    override fun getFavoriteFiction(): Flow<List<Book>> {
        return localDataSource.getFavoriteFiction().map {
            DataMapper.mapEntitiesToFictionDomain(it)
        }
    }

    override fun setFavoriteFiction(fiction: Book, state: Boolean) {
        val fictionEntity = DataMapper.mapDomainToFictionEntity(fiction)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFiction(fictionEntity, state) }
    }

    override fun getAllNonfiction(): Flow<Resource<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<NonfictionItem>>() {
            override fun loadFromDB(): Flow<List<Book>> {
                return localDataSource.getAllNonfiction().map {
                    DataMapper.mapEntitiesToNonfictionDomain(it)
                }
            }

            override fun shouldFetch(data: List<Book>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NonfictionItem>>> =
                remoteDataSource.getAllNonfiction()

            override suspend fun saveCallResult(data: List<NonfictionItem>) {
                val nonfictionList = DataMapper.mapResponsesToNonfictionEntities(data)
                localDataSource.insertNonfiction(nonfictionList)
            }
        }.asFlow()

    override fun getFavoriteNonfiction(): Flow<List<Book>> {
        return localDataSource.getFavoriteNonfiction().map {
            DataMapper.mapEntitiesToNonfictionDomain(it)
        }
    }

    override fun setFavoriteNonfiction(nonfiction: Book, state: Boolean) {
        val nonfictionEntity = DataMapper.mapDomainToNonfictionEntity(nonfiction)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteNonfiction(nonfictionEntity, state) }
    }
}