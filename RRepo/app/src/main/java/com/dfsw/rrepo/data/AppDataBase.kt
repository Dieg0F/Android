package com.dfsw.rrepo.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dfsw.rrepo.data.model.Task

@Database(
    version = 1, entities = [
        Task::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase =
            Room.databaseBuilder(
                context.applicationContext, AppDataBase::class.java, "app-database"
            ).build()
    }
}