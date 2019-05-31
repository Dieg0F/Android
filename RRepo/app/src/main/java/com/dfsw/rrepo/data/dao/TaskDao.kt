package com.dfsw.rrepo.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dfsw.rrepo.data.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task): Long

    @Insert
    fun insertList(tasks: List<Task>): List<Long>

    @Query (value = "SELECT * FROM task")
    fun getTasks(): LiveData<List<Task>>

    @Query (value = "SELECT * FROM task WHERE task.id == :taskId")
    fun getTask(taskId: Int)
}