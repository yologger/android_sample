package com.yologger.mvp

interface MainContract {

    interface View: BaseView<Presenter> {
        fun showResult(result: Int)
    }

    interface Presenter: BasePresenter {
        fun plus()
        fun minus()
    }
}