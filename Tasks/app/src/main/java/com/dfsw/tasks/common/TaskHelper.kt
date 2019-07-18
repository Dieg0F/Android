package com.dfsw.tasks.common

import android.util.Log
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class TaskHelper(taskId: Int) : KoinComponent {

    private val roomRepository: RoomRepository by inject()

    companion object {
        val TAG = Logger.TAG
    }

    init {
        Log.d(TAG, "taskId: $taskId")
    }
}