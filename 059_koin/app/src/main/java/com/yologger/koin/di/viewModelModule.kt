package com.yologger.koin.di

import com.yologger.koin.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
}