package com.dfsw.tasks.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dfsw.tasks.data.dao.TaskDao
import com.dfsw.tasks.data.model.Task

@Database(
    version = 3,
    entities = [
        Task::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    // Abstract function for TaskDao.
    abstract fun taskDao(): TaskDao
}