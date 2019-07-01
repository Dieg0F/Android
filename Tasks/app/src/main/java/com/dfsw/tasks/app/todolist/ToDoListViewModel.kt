package com.dfsw.tasks.app.todolist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.dfsw.tasks.data.AppDataBase
import com.dfsw.tasks.data.model.Task

class ToDoListViewModel : ViewModel() {

    private val profilesLiveData: LiveData<MutableList<Task>>
    private val dataBase: AppDataBase by inject
    private var lifecycleOwner: LifecycleOwner? = null

    fun setLifecycle(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    fun getAllTasks(profilesJson: String) {
        val data = dataBase.taskDao().getAllTasks()

        lifecycleOwner?.let {
            data.observe( it, Observer<MutableList<Task>> {  })
        }
    }

    fun observableTasksLiveData() = profilesLiveData

}