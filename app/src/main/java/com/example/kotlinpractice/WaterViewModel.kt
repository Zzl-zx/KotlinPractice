package com.example.kotlinpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaterViewModel : ViewModel() {
    private val _waterText = MutableLiveData("记录一下今天喝了几杯水吧")
    val waterText: LiveData<String> = _waterText
    private var waterCount = 0

    private fun updateWaterText() {
        if (waterCount == 0) _waterText.value = "今天还没喝水呢！快去喝水"
        else _waterText.value = "今天喝了$waterCount"+"杯水"
    }
    fun addWater() {
        waterCount++
        updateWaterText()
    }

    fun resetWater() {
        waterCount = 0
        updateWaterText()
    }
}