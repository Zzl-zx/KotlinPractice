package com.example.kotlinpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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

    // 后面在使用loadTodos()时已经开了协程了，自己就不能再开新的launch了
    // 改成suspend函数
    private suspend fun loadTodos() {
//        // 用协程启动loadTodos()
//        viewModelScope.launch {
//            _todoItems.value = todoDao.getAll()
//        }
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
        viewModelScope.launch {
            todoDao.insert(item)
            loadTodos()
        }

    }

    fun deleteTodo(item: TodoItem) {
        viewModelScope.launch {
            todoDao.delete(item)
            loadTodos()
        }
    }

    fun toggleTodoDone(item: TodoItem) {
        val updateItem = item.copy(done = !item.done)
        viewModelScope.launch {
            todoDao.update(updateItem)
            loadTodos()
        }
    }

}
