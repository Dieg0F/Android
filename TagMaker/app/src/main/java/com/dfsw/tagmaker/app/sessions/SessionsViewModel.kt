package com.dfsw.tagmaker.app.sessions

import android.arch.lifecycle.ViewModel
import com.dfsw.tagmaker.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SessionsViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getAllTasks() = roomRepository.getAll()
}