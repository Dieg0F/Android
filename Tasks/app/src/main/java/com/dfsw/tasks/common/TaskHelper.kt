package com.dfsw.tasks.common

import android.content.Context
import android.util.Log
import com.dfsw.tasks.R
import com.dfsw.tasks.data.model.Task
import com.dfsw.tasks.data.repository.RoomRepository
import io.reactivex.disposables.Disposable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import kotlin.concurrent.thread

class TaskHelper : KoinComponent {

    private val roomRepository: RoomRepository by inject()
    private var taskNotificationThread: Disposable? = null
    private var context: Context? = null

    companion object {
        val TAG = Logger.TAG
    }

    fun startTaskNotificationService(context: Context) {
        Log.d(TAG, "startTaskNotificationService")
        thread {
            val taskList = roomRepository.getTasksToNotify()
            taskList.forEach {
                taskNotification(it, context)
            }
        }
    }

    fun taskNotification(task: Task, context: Context) {
        val alarmTime = task.notificationFrequency
        this.context = context
        taskNotificationThread = ThreadUtils.postDelayed(alarmTime) { notify(task) }
    }

    private fun notify(task: Task) {
        roomRepository.getTask(task.id).observeForever {
            it?.let { task ->
                Log.d(TAG, "Task Name: ${task.title} ${task.notificationFrequency}")
                val taskUpdated = task
                if (taskUpdated.notificationsEnabled) {
                    context?.let { ctx ->
                        notificationHandler(ctx, taskUpdated)
                    }
                } else {
                    taskNotificationThread?.dispose()
                }
            }
        }
    }

    private fun notificationHandler(ctx: Context, task: Task) {
        Log.d(TAG, "notificationHandler")
        NotificationService().create(
            ctx,
            Constants.NOTIFICATION_CHANNEL_ID,
            "Lembrete",
            task.title,
            R.drawable.tasks_notification_icon
        ).show(ctx)

        if (task.notificationsRepeatable) {
            taskNotification(task, ctx)
        } else {
            taskNotificationThread?.dispose()
        }
    }
}