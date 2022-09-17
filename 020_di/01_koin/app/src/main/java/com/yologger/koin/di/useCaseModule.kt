package com.yologger.koin.di

import com.yologger.koin.domain.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<LoginUseCase> { LoginUseCase(get()) }
}