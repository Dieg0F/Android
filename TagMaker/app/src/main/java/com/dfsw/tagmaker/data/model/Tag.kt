package com.dfsw.tagmaker.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tags")
class Tag(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String = "",
    var createAt: Long = System.currentTimeMillis(),
    var updateAt: Long = System.currentTimeMillis()
)