package com.example.kotlinpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinpractice.databinding.FragmentWaterBinding

class WaterFragment: Fragment() {
    private  val viewModel: WaterViewModel by viewModels()
    private var _binding: FragmentWaterBinding?= null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addWaterButton.setOnClickListener {
            viewModel.addWater()
        }

        binding.resetWaterButton.setOnClickListener {
            viewModel.resetWater()
        }

        viewModel.waterText.observe(viewLifecycleOwner) { text ->
            binding.waterCountTextView.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}