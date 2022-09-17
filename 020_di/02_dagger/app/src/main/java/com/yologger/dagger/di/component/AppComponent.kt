package com.yologger.dagger.di.component

import com.yologger.dagger.di.module.PresenterModule
import com.yologger.dagger.di.module.RepositoryModule
import com.yologger.dagger.di.module.UseCaseModule
import com.yologger.dagger.domain.LoginUseCase
import com.yologger.dagger.presentation.LoginActivity
import com.yologger.dagger.presentation.LoginPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [PresenterModule::class, UseCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: LoginActivity)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(loginUseCase: LoginUseCase)
}