package com.yologger.koin.app

import android.app.Application
import com.yologger.koin.di.presenterModule
import com.yologger.koin.di.repositoryModule
import com.yologger.koin.di.useCaseModule
import com.yologger.koin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule, presenterModule, useCaseModule, repositoryModule)
        }
    }
}