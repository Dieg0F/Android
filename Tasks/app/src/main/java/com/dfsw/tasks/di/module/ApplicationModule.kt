package com.dfsw.tasks.di.module

import com.dfsw.tasks.app.createtask.CreateTaskViewModel
import com.dfsw.tasks.app.todolist.ToDoListViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    viewModel { ToDoListViewModel() }
    viewModel { CreateTaskViewModel() }
}