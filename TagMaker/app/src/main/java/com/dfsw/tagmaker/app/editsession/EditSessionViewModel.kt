package com.dfsw.tagmaker.app.editsession

import android.arch.lifecycle.ViewModel
import com.dfsw.tagmaker.data.model.Session
import com.dfsw.tagmaker.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class EditSessionViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getTaskById(taskId: Int) = roomRepository.getSession(taskId)

    fun update(session: Session, callback: (success: Boolean) -> Unit) {
        thread {
            roomRepository.updateSession(session)
            callback(true)
        }
    }
}