package com.example.kotlinpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.kotlinpractice.databinding.FragmentMoodBinding

class MoodFragment : Fragment() {

    private  val viewModel: MoodViewModel by viewModels()
    private var _binding: FragmentMoodBinding?= null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.happyButton.setOnClickListener {
            viewModel.selectMood("happy")
        }

        binding.normalButton.setOnClickListener {
            viewModel.selectMood("normal")
        }

        binding.tiredButton.setOnClickListener {
            viewModel.selectMood("tired")
        }

        viewModel.moodText.observe(viewLifecycleOwner) { text ->
            binding.moodResultTextView.text = text
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}