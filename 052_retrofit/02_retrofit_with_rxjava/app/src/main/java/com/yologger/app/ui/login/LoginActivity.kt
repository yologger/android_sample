package com.yologger.app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yologger.app.R
import com.yologger.app.databinding.ActivityLoginBinding
import com.yologger.app.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.annotations.TestOnly

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding?>(this, R.layout.activity_login).apply {
            lifecycleOwner = this@LoginActivity
            viewModel = this@LoginActivity.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        binding.buttonLogin.setOnClickListener {
            viewModel.login()
        }

        viewModel.liveState.observe(this) { state ->
            when (state) {
                is LoginViewModel.State.LoginSuccess -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                is LoginViewModel.State.LoginFailure -> {
                    when(state.error) {
                        LoginViewModel.Error.INVALID_EMAIL -> showToast("Invalid Email")
                        LoginViewModel.Error.INVALID_PASSWORD -> showToast("Invalid Password")
                        LoginViewModel.Error.NETWORK_ERROR -> showToast("Network Error")
                        else -> showToast("Client Error")
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}