package com.yologger.koin.di

import com.yologger.koin.presentation.LoginPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<LoginPresenter> { LoginPresenter(get()) }
}