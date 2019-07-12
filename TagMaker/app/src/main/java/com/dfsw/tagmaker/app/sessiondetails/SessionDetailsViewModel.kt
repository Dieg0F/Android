package com.dfsw.tagmaker.app.sessiondetails

import android.arch.lifecycle.ViewModel
import com.dfsw.tagmaker.data.model.Session
import com.dfsw.tagmaker.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class SessionDetailsViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getSession(sessionId: Int) = roomRepository.getSession(sessionId)

    fun deleteSession(session: Session, callback: () -> Unit) {
        thread {
            roomRepository.deleteSession(session)
        }
        callback()
    }
}