package com.dfsw.tagmaker.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.dfsw.tagmaker.data.model.Session

@Dao
interface SessionDao {

    @Insert
    fun insert(session: Session) : Long

    @Update
    fun update(session: Session)

    @Delete
    fun delete(session: Session)

    @Query (value = "SELECT * FROM session")
    fun getAll() : LiveData<MutableList<Session>>

    @Query (value = "SELECT * FROM session WHERE id like :sessionId" )
    fun getSession(sessionId: Int) : LiveData<Session>
}