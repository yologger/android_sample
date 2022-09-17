package com.yologger.hilt.di

import com.yologger.hilt.domain.TestUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//object UseCaseModule {
//
//    @Provides
//    @ViewModelScoped
//    fun provideTestUseCase(): TestUseCase {
//        return TestUseCase()
//    }
//}