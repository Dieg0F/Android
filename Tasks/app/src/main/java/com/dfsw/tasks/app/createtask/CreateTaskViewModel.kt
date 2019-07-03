package com.dfsw.tasks.app.createtask

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.dfsw.tasks.data.model.Task
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class CreateTaskViewModel : ViewModel(), KoinComponent {


    private val roomRepository: RoomRepository by inject()

    fun insert(task: Task, callback: ((success: Boolean) -> Unit)) {
        thread {
            roomRepository.insertTask(task).let { id ->
                callback(true)
            } ?: run {
                callback(false)
            }
        }
    }
}