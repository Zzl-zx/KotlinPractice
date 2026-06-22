package com.example.kotlinpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(

    private val onStatusClick: (TodoItem) -> Unit,
    private val onDeleteClick: (TodoItem) -> Unit

) : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.todoTitleTextView)
        private val todoIndexTextView = itemView.findViewById<TextView>(R.id.todoIndexTextView)
        private val todoStatusTextView = itemView.findViewById<TextView>(R.id.todoStatusTextView)
        private val todoDeleteButton = itemView.findViewById<Button>(R.id.todoDeleteButton)

        fun bind(
            item: TodoItem,
            position: Int,
            onDeleteClick: (TodoItem) -> Unit,
            onStatusClick: (TodoItem) -> Unit
        ) {
            titleTextView.text = item.title

            todoIndexTextView.text = "${position + 1}."

            todoStatusTextView.setOnClickListener {
                onStatusClick(item)
            }
            todoStatusTextView.text = if (item.done) "[已完成]" else "[待办]"

            todoDeleteButton.setOnClickListener {
                onDeleteClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), position, onDeleteClick, onStatusClick)
    }
}