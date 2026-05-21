package com.example.kotlinpractice

import android.content.Intent
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

        val moodButton = findViewById<Button>(R.id.moodButton)

        greetButton.setOnClickListener {
            val name = nameEditText.text.toString()
            resultTextView.text = buildGreeting(name)
        }

        moodButton.setOnClickListener {
            val intent = Intent(this, MoodActivity::class.java)
            startActivity(intent)
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
}