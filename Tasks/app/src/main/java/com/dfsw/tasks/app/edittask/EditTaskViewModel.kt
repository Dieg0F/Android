package com.dfsw.tasks.app.edittask

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.dfsw.tasks.data.model.Task
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class EditTaskViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getTaskById(taskId: Int) = roomRepository.getTask(taskId)

    fun update(task: Task, callback: (success: Boolean) -> Unit) {
        thread {
            roomRepository.updateTask(task)
            callback(true)
        }
    }
}