package com.dfsw.tasks.data.model

data class Task(
    val id: Int = 0,
    val title: String = "",
    val information: String = "",
    val status: String = "",
    val isCompleted: Boolean = false
)