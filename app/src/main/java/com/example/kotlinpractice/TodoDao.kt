package com.example.kotlinpractice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_items")
    suspend fun getAll(): List<TodoItem>

    @Insert
    suspend fun insert(item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem)

    @Update
    suspend fun update(item: TodoItem)
}