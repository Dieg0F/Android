package com.dfsw.tasks.di

import com.dfsw.tasks.app.todolist.ToDoListViewModel
import com.dfsw.tasks.data.AppDataBase
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    single { AppDataBase.getInstance(get()) }
    viewModel { ToDoListViewModel() }
}