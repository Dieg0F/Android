package com.dfsw.tasks.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.dfsw.tasks.data.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task) : Long

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query (value = "SELECT * FROM task")
    fun getAllTasks() : LiveData<MutableList<Task>>

    @Query (value = "SELECT * FROM task WHERE notificationsEnabled == 1")
    fun getTasksToNotify() : MutableList<Task>

    @Query (value = "SELECT * FROM task WHERE isCompleted == :isCompleted")
    fun getTaskByCompleted(isCompleted: Boolean) : LiveData<MutableList<Task>>

    @Query (value = "SELECT * FROM task WHERE id like :taskId" )
    fun getTask(taskId: Int) : LiveData<Task>
}