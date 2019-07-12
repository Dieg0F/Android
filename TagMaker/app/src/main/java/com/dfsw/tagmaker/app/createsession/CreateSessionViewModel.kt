package com.dfsw.tagmaker.app.createsession

import android.arch.lifecycle.ViewModel
import com.dfsw.tagmaker.data.model.Session
import com.dfsw.tagmaker.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class CreateSessionViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun insert(session: Session, callback: ((success: Boolean) -> Unit)) {
        thread {
            roomRepository.insertSession(session).let { id ->
                callback(true)
            } ?: run {
                callback(false)
            }
        }
    }
}