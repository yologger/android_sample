package com.yologger.project.presentation.login

import android.text.Editable
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yologger.project.domain.LoginError
import com.yologger.project.domain.LoginResult
import com.yologger.project.domain.LoginUseCase
import com.yologger.project.presentation.extension.combineWith
import com.yologger.project.presentation.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    enum class Status {
        IDLE,
        USER_NOT_EXIST,
        SERVER_ERROR,
        INVALID_EMAIL_FORMAT,
        INVALID_PASSWORD_FORMAT
    }

    val status: MutableLiveData<Status> by lazy { MutableLiveData<Status>().apply { value = Status.IDLE } }

    fun login(email: String, password: String) {

        if (!areAllInputsValid(email, password)) {
            return
        }

//        loginUseCase.execute()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(onNext = {
//                when(it) {
//                    is LoginResult.SUCCESS -> {
//
//                    }
//                    is LoginResult.FAILURE -> {
//                        when(it.error) {
//                            LoginError.USER_NOT_EXIST -> {}
//                            LoginError.SERVER_ERROR -> {}
//                        }
//                    }
//                }
//            }, onError = {
//
//            }, onComplete = {
//
//            })
//            .addTo(disposables)

    }

    private fun areAllInputsValid(email: String, password: String): Boolean {
        if (!isEmailValid(email)) {
            status.value = Status.INVALID_EMAIL_FORMAT
            return false
        }
        if (!isPasswordValid(password)) {
            status.value = Status.INVALID_PASSWORD_FORMAT
            return false
        }
        return true
    }

    private fun isEmailValid(text: CharSequence): Boolean {
//        return !text.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(text).matches()
        return !text.isNullOrEmpty()
    }

    private fun isPasswordValid(text: CharSequence): Boolean {
        return text.length > 8
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
