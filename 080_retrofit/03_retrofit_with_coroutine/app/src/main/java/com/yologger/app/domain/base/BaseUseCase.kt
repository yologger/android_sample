package com.yologger.app.domain.base

interface BaseUseCase<Params, Return> {
    suspend fun execute(params: Params? = null): Return
}