package com.dfsw.tasks.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat.getSystemService
import com.dfsw.tasks.R
import com.dfsw.tasks.common.Containts.CHANNEL_ID
import com.dfsw.tasks.data.model.Task
import com.dfsw.tasks.data.repository.RoomRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object NotificationsService : KoinComponent {

    private val roomRepository: RoomRepository by inject()
    private val context: Context by inject()

//    fun start(lifecycleOwner: LifecycleOwner, context: Context){
//        roomRepository.getAllTasks().observe(Observer<MutableList<Task>> {
//
//        })
//    }

    fun createNotification(context: Context) {
        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.tasks_notification_icon)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).apply {
            notify(0, builder.build())
        }
    }

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TASKS_CHANNEL"
            val descriptionText = "TASKS_CHANNEL DESCRIPTION"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}