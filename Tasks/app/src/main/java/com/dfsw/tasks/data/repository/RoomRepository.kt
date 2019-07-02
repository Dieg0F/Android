package com.dfsw.tasks.data.repository

import com.dfsw.tasks.data.AppDataBase
import com.dfsw.tasks.data.model.Task
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RoomRepository : KoinComponent {

    private val appDataBase: AppDataBase by inject()

    fun insertTask(task: Task) = appDataBase.taskDao().insert(task)

    fun updateTask(task: Task) = appDataBase.taskDao().update(task)

    fun deleteTask(task: Task) = appDataBase.taskDao().delete(task)

    fun getAllTasks() = appDataBase.taskDao().getAllTasks()
}