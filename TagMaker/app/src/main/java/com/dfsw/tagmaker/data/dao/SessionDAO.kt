package com.dfsw.tagmaker.data.dao

import android.content.pm.PackageInstaller
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dfsw.tagmaker.data.model.Session

@Dao
interface SessionDAO {

    @Insert
    fun insert(session: Session) : Long

    @Update
    fun update(session: Session)

    @Delete
    fun delete(session: Session)

    @Query(value = "SELECT * FROM sessions")
    fun getAll() : LiveData<MutableList<Session>>

    @Query (value = "SELECT * FROM sessions WHERE id like :sessionId" )
    fun getSession(sessionId: Int) : LiveData<Session>
}