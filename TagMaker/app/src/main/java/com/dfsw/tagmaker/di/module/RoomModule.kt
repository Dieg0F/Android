package com.dfsw.tagmaker.di.module

import androidx.room.Room
import com.dfsw.tagmaker.common.Constants.APP_DATABASE_NAME
import com.dfsw.tagmaker.data.AppDataBase
import com.dfsw.tagmaker.data.repository.RoomRepository
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