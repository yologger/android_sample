package com.yologger.app.ui.main

import com.yologger.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel() {
    var buttonText = "clicked"
}