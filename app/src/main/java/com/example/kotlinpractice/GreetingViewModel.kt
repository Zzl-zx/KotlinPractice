package com.example.kotlinpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingViewModel : ViewModel() {
    private val _greetingText = MutableLiveData("")
    val greetingText: LiveData<String> = _greetingText

    fun greeting(name: String) {
        val displayName = name.ifBlank { "路人甲" }
        _greetingText.value = "你好，$displayName，今天也要好好活着！"
    }
}