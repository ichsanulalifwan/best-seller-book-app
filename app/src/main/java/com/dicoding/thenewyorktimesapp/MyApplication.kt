package com.dicoding.thenewyorktimesapp

import android.app.Application
import com.dicoding.thenewyorktimesapp.core.di.databaseModule
import com.dicoding.thenewyorktimesapp.core.di.networkModule
import com.dicoding.thenewyorktimesapp.core.di.repositoryModule
import com.dicoding.thenewyorktimesapp.di.useCaseModule
import com.dicoding.thenewyorktimesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

@Suppress("unused")
class MyApplication : Application() {
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

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}