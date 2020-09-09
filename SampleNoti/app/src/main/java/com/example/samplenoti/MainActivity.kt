package com.example.samplenoti

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

const val CHANNEL_ID = "channel1"
const val CHANNEL_NAME = "Channel1"

const val CHANNEL_ID2 = "channel2"
const val CHANNEL_NAME2 = "Channel2"

class MainActivity : AppCompatActivity() {
    lateinit var manager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            showNoti1()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            showNoti2()
        }
    }

    fun showNoti1() {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT))

            builder = NotificationCompat.Builder(this, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder.setContentTitle("간단 알림")
        builder.setContentText("알림 메시지 입니다.")
        builder.setSmallIcon(android.R.drawable.ic_menu_view)
        val noti = builder.build()

        manager.notify(1, noti) // 상단 알림 띄우기
    }

    fun showNoti2() {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID2,
                    CHANNEL_NAME2,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )

            builder = NotificationCompat.Builder(this, CHANNEL_ID2)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val style = NotificationCompat.BigTextStyle()
        style.bigText("많은 글자들 입니다 많은 글자들 입니다 많은 글자들 입니다 많은 글자들 입니다 ")
        style.setBigContentTitle("제목입니다")
        style.setSummaryText("요약 글입니다")

        builder.setContentTitle("간단 알림")
        builder.setContentText("알림 메시지 입니다.")
        builder.setSmallIcon(android.R.drawable.ic_menu_view)
        builder.setAutoCancel(true) // 알림을 클릭했을 때 자동으로 알림 표시 삭제
        builder.setContentIntent(pendingIntent)
        builder.setStyle(style)

        val noti = builder.build()

        manager.notify(2, noti)
    }
}
