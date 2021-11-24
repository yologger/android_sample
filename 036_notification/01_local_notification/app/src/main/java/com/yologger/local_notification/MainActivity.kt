package com.yologger.local_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    lateinit var button: Button

    val NOTIFICATION_GROUP_ID = 0
    val NOTIFICATION_1_ID = 1
    val NOTIFICATION_2_ID = 2
    val NOTIFICATION_3_ID = 3

    val NOTIFICATION_GROUP_KEY = "notification_group_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.activity_main_btn)
        button.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channelId = "channel_id"
                val channelName = "channel_name"
                val channelDescription = "channel_description"
                val importance = NotificationManager.IMPORTANCE_HIGH

                val notificationChannel = NotificationChannel(channelId, channelName, importance)
                notificationChannel.description = channelDescription

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)

                val notification1 = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("A Notification 1")
                        .setContentText("This is a notification 1")
                        .setGroup(NOTIFICATION_GROUP_KEY)
                        .build()

                val notification2 = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("A Notification 2")
                        .setContentText("This is a notification 2")
                        .setGroup(NOTIFICATION_GROUP_KEY)
                        .build()

                val notification3 = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("A Notification 3")
                        .setContentText("This is a notification 3")
                        .setGroup(NOTIFICATION_GROUP_KEY)
                        .build()

                val summaryNotification = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Summary Notification")
                        .setContentText("This is a summary notification")
                        .setGroup(NOTIFICATION_GROUP_KEY)
                        .setGroupSummary(true)
                        .build()

                notificationManager.notify(NOTIFICATION_1_ID, notification1)
                notificationManager.notify(NOTIFICATION_2_ID, notification2)
                notificationManager.notify(NOTIFICATION_3_ID, notification3)
                notificationManager.notify(NOTIFICATION_GROUP_ID, summaryNotification)



            } else {
//                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//                val notificationBuilder = NotificationCompat.Builder(this)
//                        .setSmallIcon(android.R.drawable.ic_dialog_info)
//                        .setContentTitle("A Notification")
//                        .setContentText("This is a notification")
//
//                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
            }
        }
    }
}