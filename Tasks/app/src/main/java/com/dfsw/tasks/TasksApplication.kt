package com.dfsw.tasks

import android.app.Application
import com.dfsw.tasks.di.koinModule
import org.koin.android.ext.android.startKoin

class TasksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinModule)
    }
}