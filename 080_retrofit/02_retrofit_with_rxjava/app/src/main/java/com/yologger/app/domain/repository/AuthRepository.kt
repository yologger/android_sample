package com.yologger.app.domain.repository

import com.yologger.app.domain.login.LoginResult
import io.reactivex.rxjava3.core.Observable

interface AuthRepository {
    fun login(email: String, password: String): Observable<LoginResult>
}