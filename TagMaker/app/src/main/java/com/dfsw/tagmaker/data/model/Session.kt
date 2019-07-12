package com.dfsw.tagmaker.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var status: String = "",
    var tags: String = "",
    var createAt: Long = System.currentTimeMillis(),
    var updateAt: Long = System.currentTimeMillis()
)