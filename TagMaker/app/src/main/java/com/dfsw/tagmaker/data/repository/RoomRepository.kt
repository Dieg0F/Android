package com.dfsw.tagmaker.data.repository

import com.dfsw.tagmaker.data.AppDataBase
import com.dfsw.tagmaker.data.model.Session
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RoomRepository : KoinComponent {

    private val appDataBase: AppDataBase by inject()

    fun insert(session: Session) = appDataBase.sessionDAO().insert(session)

    fun update(session: Session) = appDataBase.sessionDAO().update(session)

    fun delete(session: Session) = appDataBase.sessionDAO().delete(session)

    fun getAll() = appDataBase.sessionDAO().getAll()

    fun getSession(sessionId: Int) = appDataBase.sessionDAO().getSession(sessionId)
}