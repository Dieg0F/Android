package com.dfsw.tasks.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var status: String = "",
    var notificationsEnabled: Boolean = false,
    var notificationsRepeatable: Boolean = false,
    var notificationFrequency: Long = 0L,
    var isCompleted: Boolean = false
)