package com.dicoding.thenewyorktimesapp.core.di

import androidx.room.Room
import com.dicoding.thenewyorktimesapp.core.data.BookRepository
import com.dicoding.thenewyorktimesapp.core.data.source.local.LocalDataSource
import com.dicoding.thenewyorktimesapp.core.data.source.local.room.BookDatabase
import com.dicoding.thenewyorktimesapp.core.data.source.remote.RemoteDataSource
import com.dicoding.thenewyorktimesapp.core.data.source.remote.network.ApiService
import com.dicoding.thenewyorktimesapp.core.domain.repository.IBookRepository
import com.dicoding.thenewyorktimesapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val URL = "https://api.nytimes.com/svc/books/v3/lists/"

val databaseModule = module {

    factory { get<BookDatabase>().bookDao() }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("book".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            BookDatabase::class.java, "Book.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {

    single {

        val hostname = "api.nytimes.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/9sbeva/dVYU/QXkEG0CfdTcK1ga+5SvjzPKRtg3RHNE=")
            .add(hostname, "sha256/4a6cPehI7OG6cuDZka5NDZ7FR8a60d3auda+sKfg4Ng=")
            .add(hostname, "sha256/x4QzPSC810K5/cMjb05Qm4k3Bw5zBn4lTdO/nEW/Td4=")
            .add(hostname, "sha256/vRU+17BDT2iGsXvOi76E7TQMcTLXAqj0+jGPdW7L1vM=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IBookRepository> {
        BookRepository(
            get(),
            get(),
            get()
        )
    }
}
