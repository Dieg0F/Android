package com.dfsw.tagmaker.di

import com.dfsw.tagmaker.di.module.applicationModule
import com.dfsw.tagmaker.di.module.roomModule

val koinModule = listOf(
    applicationModule,
    roomModule
)