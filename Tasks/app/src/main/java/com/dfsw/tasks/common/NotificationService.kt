package com.dfsw.tasks.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.RemoteInput
import com.dfsw.tasks.common.Constants.NOTIFICATION_CHANNEL_ID
import com.dfsw.tasks.common.Constants.NOTIFICATION_CHANNEL_NAME

class NotificationService {

    /**
     * Create a notification channel. Needed for Android O and above.
     *
     * @param context to get the system service.
     */
    fun createNotificationChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun create(
        context: Context,
        channelId: String,
        title: String,
        body: String,
        icon: Int,
        sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
        autoCancel: Boolean = true,
        style: NotificationCompat.Style = NotificationCompat.BigTextStyle()
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(icon)
            .setSound(sound)
            .setAutoCancel(autoCancel)
            .setStyle(style)
    }
}

fun NotificationCompat.Builder.show(context: Context) {
    val systemService = context.getSystemService(Context.NOTIFICATION_SERVICE)
    if (systemService is NotificationManager) {
        NotificationService().createNotificationChannel(context)
        val notificationManager: NotificationManager = systemService
        notificationManager.notify(this.hashCode(), this.build())
    }
}

fun NotificationCompat.Builder.reply(
    label: String,
    pendingIntent: PendingIntent,
    replyIcon: Int
): NotificationCompat.Builder {
    val replyLabel: String = label
    val resultKey = "key_text_reply"
    val remoteInput: RemoteInput = RemoteInput.Builder(resultKey).run {
        setLabel(replyLabel)
        build()
    }

    val action: NotificationCompat.Action =
        NotificationCompat.Action.Builder(
            replyIcon,
            label,
            pendingIntent
        )
            .addRemoteInput(remoteInput)
            .build()

    this.addAction(action)
    return this
}