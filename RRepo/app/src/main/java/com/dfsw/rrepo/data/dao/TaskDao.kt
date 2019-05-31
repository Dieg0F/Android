package com.dfsw.rrepo.data.dao

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
    fun getTesks(): List<Task>
}