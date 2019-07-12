package com.dfsw.tagmaker

import android.app.Application
import com.dfsw.tagmaker.di.koinModule
import org.koin.android.ext.android.startKoin

class TasksApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinModule)
    }
}