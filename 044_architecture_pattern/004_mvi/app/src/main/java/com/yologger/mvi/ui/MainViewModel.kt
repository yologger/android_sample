package com.yologger.mvi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class MainIntent {
    object PlusValue: MainIntent()
    object MinusValue: MainIntent()
    object ClearValue: MainIntent()
}

sealed class MainState {
    object Idle: MainState()
    data class Updated(val value: Int): MainState()
    object Loading: MainState()
}

class MainViewModel: ViewModel() {

    val intent = Channel<MainIntent>(Channel.UNLIMITED)

    private var _value = 0
    private val _state = MutableStateFlow<MainState>(MainState.Updated(_value))
    val state = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.PlusValue -> plus()
                    is MainIntent.MinusValue -> minus()
                    is MainIntent.ClearValue -> clear()
                }
            }
        }
    }

    private fun plus() {
        _value += 1
        _state.value = MainState.Updated(_value)
    }

    private fun minus() {
        _value -= 1
        _state.value = MainState.Updated(_value)
    }

    private fun clear() {
        _value = 0
        _state.value = MainState.Updated(0)
    }
}