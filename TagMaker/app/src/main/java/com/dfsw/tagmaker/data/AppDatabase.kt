package com.dfsw.tagmaker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dfsw.tagmaker.data.dao.SessionDAO
import com.dfsw.tagmaker.data.model.Session

@Database(version = 1, entities = [ Session::class ])
abstract class AppDataBase : RoomDatabase() {

    // Abstract function for TaskDao.
     abstract fun sessionDAO(): SessionDAO
}