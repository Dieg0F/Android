package com.dfsw.tasks.di

import com.dfsw.tasks.di.module.applicationModule
import com.dfsw.tasks.di.module.roomModule

val koinModule = listOf(
    applicationModule,
    roomModule
)