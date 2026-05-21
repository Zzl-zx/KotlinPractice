package com.example.kotlinpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodViewModel : ViewModel(){
    private val _moodText = MutableLiveData("选择一个心情吧")
    val moodText: LiveData<String> = _moodText

    fun selectMood(mood: String) {
        _moodText.value = when (mood) {
            "happy" -> "今天不错哟"
            "normal" -> "平稳的一天也挺好"
            "tired" -> "降低难度，完成小任务"
            else -> "记录一下"
        }
    }
}
