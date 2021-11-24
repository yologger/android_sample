package com.yologger.hilt.di

import com.yologger.hilt.data.TestRepository
import com.yologger.hilt.data.TestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTestRepository(testRepositoryImpl: TestRepositoryImpl): TestRepository
}