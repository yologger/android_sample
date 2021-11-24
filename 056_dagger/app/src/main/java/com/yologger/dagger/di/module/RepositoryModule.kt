package com.yologger.dagger.di.module

import com.yologger.dagger.data.UserRepository
import com.yologger.dagger.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}