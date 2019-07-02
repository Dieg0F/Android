package com.dfsw.tasks.app.todolist

import android.arch.lifecycle.ViewModel
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ToDoListViewModel : ViewModel(), KoinComponent {

    private val roomRepository: RoomRepository by inject()

    fun getAllTasks() = roomRepository.getAllTasks()
}