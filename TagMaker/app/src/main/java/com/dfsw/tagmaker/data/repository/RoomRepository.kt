package com.dfsw.tagmaker.data.repository

import com.dfsw.tagmaker.data.AppDataBase
import com.dfsw.tagmaker.data.model.Session
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RoomRepository : KoinComponent {

    private val appDataBase: AppDataBase by inject()

    fun insertSession(session: Session) = appDataBase.sessionDao().insert(session)

    fun updateSession(session: Session) = appDataBase.sessionDao().update(session)

    fun deleteSession(session: Session) = appDataBase.sessionDao().delete(session)

    fun getAll() = appDataBase.sessionDao().getAll()

    fun getSession(sessionId: Int) = appDataBase.sessionDao().getSession(sessionId)
}