package com.yologger.dagger.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yologger.dagger.R
import com.yologger.dagger.app.App
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private val buttonLogin: Button by lazy { findViewById(R.id.activity_login_btn_login) }

    @Inject lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as App).appComponent.inject(this)

        buttonLogin.setOnClickListener {
            loginPresenter.login()
        }
    }
}