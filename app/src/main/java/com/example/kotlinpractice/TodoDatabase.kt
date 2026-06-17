package com.example.kotlinpractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 这是一个Room数据库定义类
@Database(
    // 这个数据库里面包含的表有TodoItem这一张
    entities = [TodoItem::class],
    // 数据库版本
    version = 1
)

// room要我写个继承RoomDatabase的抽象类，它会在编译时帮我生成真正实现
abstract class TodoDatabase : RoomDatabase() {
    // 从这个数据库入口里可以拿到TodoDao
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile // 保证多线程下读到的是最新值
        private var INSTANCE: TodoDatabase ?= null // 可空变量

        // 对外暴露的唯一入口，想拿数据库不直接new，走这里
        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                //room真正创建数据库实例的地方
                val instance = Room.databaseBuilder(
                    context.applicationContext,   // 应用级contex，context作用是告诉room数据库文件放在哪里，避免内存泄露
                    TodoDatabase::class.java,   //告诉room要创建哪个数据库类
                    "todo_database"   // 数据库文件名
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}