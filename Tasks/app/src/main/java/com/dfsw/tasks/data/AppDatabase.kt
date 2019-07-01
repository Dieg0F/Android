package com.dfsw.tasks.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dfsw.tasks.data.dao.TaskDao
import com.dfsw.tasks.data.model.Task

@Database(
    version = 1,
    entities = [
        Task::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

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