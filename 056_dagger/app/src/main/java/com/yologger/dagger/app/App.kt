package com.yologger.dagger.app

import android.app.Application
import com.yologger.dagger.di.component.AppComponent
import com.yologger.dagger.di.component.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

    }
}