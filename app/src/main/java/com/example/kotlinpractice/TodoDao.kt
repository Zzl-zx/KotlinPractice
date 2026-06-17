package com.example.kotlinpractice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_items")
    fun getAll(): List<TodoItem>

    @Insert
    fun insert(item: TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Update
    fun update(item: TodoItem)
}