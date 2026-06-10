package com.example.kotlinpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpractice.databinding.FragmentTodoBinding

class TodoFragment: Fragment() {
    private val viewModel: TodoViewModel by viewModels()
    private var _binding: FragmentTodoBinding?= null
    private val binding get() = _binding!!
    private val todoAdapter = TodoAdapter(
        onDeleteClick = { item ->
            viewModel.deleteTodo(item)
        },
        onStatusClick = { item ->
            viewModel.toggleTodoDone(item)
        }
    )

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

        binding.todoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.todoRecyclerView.adapter = todoAdapter

        viewModel.todoItems.observe(viewLifecycleOwner) { items ->
            todoAdapter.submitList(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}