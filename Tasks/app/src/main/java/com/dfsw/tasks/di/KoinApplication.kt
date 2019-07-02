package com.dfsw.tasks.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        startKoin(this, listOf(com.dfsw.tasks.di.module.applicationModule))
        super.onCreate()
    }
}