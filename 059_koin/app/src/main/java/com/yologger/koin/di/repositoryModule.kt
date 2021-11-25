package com.yologger.koin.di

import com.yologger.koin.data.UserRepository
import com.yologger.koin.data.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl() }
}