package com.dfsw.tasks.app.taskdetails

import android.arch.lifecycle.ViewModel
import com.dfsw.tasks.data.model.Task
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class TaskDetailsViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getTaskById(taskId: Int) = roomRepository.getTask(taskId)

    fun deleteTask(task: Task, callback: () -> Unit) {
        thread {
            roomRepository.deleteTask(task)
        }
        callback()
    }
}