package com.dicoding.thenewyorktimespp

import android.app.Application
import com.dicoding.thenewyorktimespp.core.di.databaseModule
import com.dicoding.thenewyorktimespp.core.di.networkModule
import com.dicoding.thenewyorktimespp.core.di.repositoryModule
import com.dicoding.thenewyorktimespp.di.useCaseModule
import com.dicoding.thenewyorktimespp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}