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

    fun insert(task: Task) {
        thread {
            if (roomRepository.insertTask(task) != null) {
                Log.d("CreateTaskViewModel", "Task Created!")
                // Return success!!
            } else {
                Log.e("CreateTaskViewModel", "Task Not Created!")
                // Return fail!!
            }
        }
    }

}