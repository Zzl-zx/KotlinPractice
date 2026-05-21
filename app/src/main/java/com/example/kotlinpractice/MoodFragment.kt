package com.example.kotlinpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels

class MoodFragment : Fragment(R.layout.fragment_mood) {

    private  val viewModel: MoodViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val happyButton = view.findViewById<Button>(R.id.happyButton)
        val normalButton = view.findViewById<Button>(R.id.normalButton)
        val tiredButton = view.findViewById<Button>(R.id.tiredButton)
        val moodResultTextView = view.findViewById<TextView>(R.id.moodResultTextView)

        happyButton.setOnClickListener {
            viewModel.selectMood("happy")
        }
        normalButton.setOnClickListener {
            viewModel.selectMood("normal")
        }
        tiredButton.setOnClickListener {
            viewModel.selectMood("tired")
        }

        viewModel.moodText.observe(viewLifecycleOwner) { text ->
            moodResultTextView.text = text
        }

    }

}