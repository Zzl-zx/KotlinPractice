package com.example.kotlinpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var items: List<TodoItem> = emptyList()

    class TodoViewHolder(val binding: ItemTodoBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoAdapter.TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        val mark = if (item.done) "[done]" else "[todo]"

        holder.binding.todoStatusTextView.text = mark
        holder.binding.todoTitleTextView.text = item.title

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(newItems: List<TodoItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}