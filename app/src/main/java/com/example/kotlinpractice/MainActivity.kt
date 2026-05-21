package com.example.kotlinpractice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val greetButton = findViewById<Button>(R.id.greetButtton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val happyButton = findViewById<Button>(R.id.happyButton)
        val normalButton = findViewById<Button>(R.id.normalButton)
        val tiredButton = findViewById<Button>(R.id.tiredButton)
        val moodResultTextView = findViewById<TextView>(R.id.moodResultTextView)

        greetButton.setOnClickListener {
            val name = nameEditText.text.toString()
            resultTextView.text = buildGreeting(name)
        }

        happyButton.setOnClickListener {
            moodResultTextView.text = getMoodText("happy")
        }
        normalButton.setOnClickListener {
            moodResultTextView.text = getMoodText("normal")
        }
        tiredButton.setOnClickListener {
            moodResultTextView.text = getMoodText("tired")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun buildGreeting(name: String): String {
        val displayName = name.ifBlank { "游客" }
        return "你好，$displayName，今天也要好好生活"
    }

    fun getMoodText(mood: String): String {
        return when (mood) {
            "happy" -> "今天不错呦"
            "normal" -> "今天一般般呀"
            "tired" -> "降低难度，苟过一天"
            else -> "记录今天第状态"
        }
    }
}