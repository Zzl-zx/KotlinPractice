package com.example.kotlinpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDatabase(application).todoDao()

    // 内部可改
    private val _todoItems = MutableLiveData<List<TodoItem>>(emptyList())
    //外部只读
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _todoItems.value = todoDao.getAll()
    }

    // fragment传进来的是字符串，viewmodel把字符串包装成TodoItem
    fun addTodo(todo: String) {
        if (todo.isBlank()) return

        // 创建一个todoItem
        val item = TodoItem(
            title = todo,
            done = false
        )
        todoDao.insert(item)
        loadTodos()
    }

    fun deleteTodo(item: TodoItem) {
        todoDao.delete(item)
        loadTodos()
    }

    fun toggleTodoDone(item: TodoItem) {
        val updateItem = item.copy(done = !item.done)
        todoDao.update(updateItem)
        loadTodos()
    }

}
