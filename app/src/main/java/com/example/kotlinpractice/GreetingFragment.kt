package com.example.kotlinpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinpractice.databinding.FragmentGreetingBinding

class GreetingFragment : Fragment() {

    private val viewModel: GreetingViewModel by viewModels()
    private var _binding: FragmentGreetingBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGreetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.greetButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            viewModel.greeting(name)
            binding.resultTextView.visibility = View.VISIBLE
        }
        viewModel.greetingText.observe(viewLifecycleOwner) { text ->
            binding.resultTextView.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}