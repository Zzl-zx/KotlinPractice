package com.example.kotlinpractice

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    fun getAll(): Flow<List<TodoItem>> {
        return todoDao.getAll()
    }

    suspend fun insert(item: TodoItem) {
        todoDao.insert(item)
    }

    suspend fun delete(item: TodoItem) {
        todoDao.delete(item)
    }

    suspend fun update(item: TodoItem) {
        todoDao.update(item)
    }

}