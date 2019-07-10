package com.dfsw.tagmaker.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dfsw.tagmaker.data.dao.SessionDao
import com.dfsw.tagmaker.data.model.Session

@Database(
    version = 2,
    entities = [
        Session::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    // Abstract function for SessionDao.
    abstract fun sessionDao(): SessionDao
}