package com.example.kotlinpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinpractice.databinding.FragmentTodoBinding

class TodoFragment: Fragment() {
    private val viewModel: TodoViewModel by viewModels()
    private var _binding: FragmentTodoBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTodoButton.setOnClickListener {
            val todo = binding.todoEditText.text.toString()
            viewModel.addTodo(todo)
            binding.todoEditText.text.clear()
        }

        viewModel.todoItems.observe(viewLifecycleOwner) { items ->
            val text = buildString {
                append("今日待办：\n")
                items.forEachIndexed { index, item ->
                    val mark = if (item.done) "[done]" else "[todo]"
                    append("${index + 1}. $mark ${item.title}\n")
                }
            }
            binding.todoListTextView.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}