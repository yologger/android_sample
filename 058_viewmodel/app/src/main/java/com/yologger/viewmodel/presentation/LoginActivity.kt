package com.yologger.viewmodel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.yologger.viewmodel.R
import com.yologger.viewmodel.domain.LoginUseCase

class LoginActivity : AppCompatActivity() {

    private val loginUseCase: LoginUseCase by lazy { LoginUseCase() }

    private val loginViewModel by viewModels<LoginViewModel> { LoginViewModelFactory(loginUseCase) }

    private val loginButton: Button by lazy { findViewById(R.id.activity_login_btn_login) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            loginViewModel.login()
        }
    }
}

