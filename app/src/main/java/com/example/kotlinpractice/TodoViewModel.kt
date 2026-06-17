package com.example.kotlinpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = TodoDatabase.getDatabase(application).todoDao()

    // 内部可改
    private val _todoItems = MutableLiveData<List<TodoItem>>(emptyList())
    //外部只读
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    init {
        viewModelScope.launch {
            loadTodos()
        }
    }


    private suspend fun loadTodos() {
        val items = withContext(Dispatchers.IO) {
            todoDao.getAll()
        }
        _todoItems.value = items
    }

    private fun runDatabaseAction(action:suspend () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                action()
            }
            loadTodos()
        }
    }

    // fragment传进来的是字符串，viewmodel把字符串包装成TodoItem
    fun addTodo(todo: String) {
        if (todo.isBlank()) return

        // 创建一个todoItem
        val item = TodoItem(
            title = todo,
            done = false
        )
        runDatabaseAction {
            todoDao.insert(item)
        }
    }

    fun deleteTodo(item: TodoItem) {
        runDatabaseAction { todoDao.delete(item) }
    }

    fun toggleTodoDone(item: TodoItem) {
        val updateItem = item.copy(done = !item.done)
        runDatabaseAction { todoDao.update(updateItem) }
    }
}
