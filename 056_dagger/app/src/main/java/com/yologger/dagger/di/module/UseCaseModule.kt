package com.yologger.dagger.di.module

import com.yologger.dagger.data.UserRepository
import com.yologger.dagger.domain.LoginUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class UseCaseModule {

    // @Provides
    // fun provideLoginUseCase(): LoginUseCase {
    //     return LoginUseCase()
    // }

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository): LoginUseCase {
        return LoginUseCase(userRepository)
    }
}