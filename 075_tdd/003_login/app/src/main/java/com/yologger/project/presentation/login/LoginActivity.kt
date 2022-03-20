package com.yologger.project.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.yologger.project.R
import com.yologger.project.databinding.ActivityLoginBinding
import com.yologger.project.presentation.join.JoinActivity
import com.yologger.project.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        observeViewModel()
    }

    private fun setupBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun observeViewModel() {
        loginViewModel.status.observe(this) {
            when (it) {
                LoginViewModel.Status.IDLE -> {}
                LoginViewModel.Status.USER_NOT_EXIST -> {}
                LoginViewModel.Status.SERVER_ERROR -> {}
                LoginViewModel.Status.INVALID_EMAIL_FORMAT -> showErrorDialog("Invalid email")
                LoginViewModel.Status.INVALID_PASSWORD_FORMAT -> showErrorDialog("Invalid password")
            }
        }
    }

    fun onButtonClicked(view: View) {
        when (view.id) {
            R.id.activity_login_btn_login -> {
                val email = binding.activityLoginEtEmail.text!!.toString()
                val password = binding.activityLoginEtPassword.text!!.toString()
                loginViewModel.login(email, password)
            }
            R.id.activity_login_btn_join -> navigateToJoinScreen()
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToJoinScreen() {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> }
            .create()
            .show()
    }
}