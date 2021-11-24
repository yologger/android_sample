package com.yologger.mvp

import android.util.Log

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private val repository = Repository()

    override fun start() {
        val result = repository.get()
        view.showResult(result)
    }

    override fun plus() {
        repository.plus()
        view.showResult(repository.get())
    }

    override fun minus() {
        repository.minus()
        view.showResult(repository.get())
    }
}