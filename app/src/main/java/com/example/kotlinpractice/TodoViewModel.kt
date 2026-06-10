package com.example.kotlinpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val todoList = mutableListOf<TodoItem>()

    // 内部可改
    private val _todoItems = MutableLiveData<List<TodoItem>>(emptyList())
    //外部只读
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    // fragment传进来的是字符串，viewmodel把字符串包装成TodoItem
    fun addTodo(todo: String) {
        if (todo.isBlank()) return
        // 创建一个todoItem
        val item = TodoItem(
            id = todoList.size + 1,
            title = todo,
            done = false
        )
        // 把item加进可变列表
        todoList.add(item)
        // 把当前列表内容作为一个新列表发给观察者
        _todoItems.value = todoList.toList()
    }

    fun deleteTodo(item: TodoItem) {
        todoList.remove(item)
        _todoItems.value = todoList.toList()
    }

    fun toggleTodoDone(item: TodoItem) {
        // 找到这条item在列表中的位置
        val index = todoList.indexOf(item)
        if (index == -1) return

        // 基于旧对象创建一个新对象，只把done反过来
        val updatedItem = item.copy(done = !item.done)
        // 用新对象替换旧对象
        todoList[index] = updatedItem
        _todoItems.value = todoList.toList()
    }


}
