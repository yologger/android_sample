package com.yologger.dagger.di.module

import com.yologger.dagger.domain.LoginUseCase
import com.yologger.dagger.presentation.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

//    @Provides
//    fun provideLoginPresenter(): LoginPresenter {
//        return LoginPresenter()
//    }

    @Provides
    fun provideLoginPresenter(loginUseCase: LoginUseCase): LoginPresenter {
        return LoginPresenter(loginUseCase)
    }
}