package com.dfsw.rrepo.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.dfsw.rrepo.data.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task) : Long

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Insert
    fun insertList(tasks: List<Task>): List<Long>

    @Query (value = "SELECT * FROM task")
    fun getTasks(): LiveData<List<Task>>

    @Query (value = "SELECT * FROM task WHERE id == :taskId")
    fun getTask(taskId: Int) : LiveData<Task>
}