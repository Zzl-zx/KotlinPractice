package com.example.kotlinpractice

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val showGreetingButton = findViewById<Button>(R.id.showGreetingButton)
        val showWaterButton = findViewById<Button>(R.id.showWaterButton)
        val showMoodButton = findViewById<Button>(R.id.showMoodButton)
        val showTodoButton= findViewById<Button>(R.id.showTodoButton)

        showGreetingButton.setOnClickListener {
            showFragment(GreetingFragment())
        }
        showWaterButton.setOnClickListener {
            showFragment(WaterFragment())
        }
        showMoodButton.setOnClickListener {
            showFragment(MoodFragment())
        }
        showTodoButton.setOnClickListener {
            showFragment(TodoFragment())
        }
        if (savedInstanceState == null) {
            showFragment(GreetingFragment())
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, fragment)
            .commit()
    }
}