package com.dfsw.tasks.di.module

import android.arch.persistence.room.Room
import com.dfsw.tasks.common.Constants.APP_DATABASE_NAME
import com.dfsw.tasks.data.AppDataBase
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.dsl.module.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(),
            AppDataBase::class.java, APP_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { RoomRepository() }
}