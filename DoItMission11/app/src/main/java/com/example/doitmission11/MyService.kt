package com.example.doitmission11

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            return Service.START_STICKY
        } else {
            val contents = intent.getStringExtra("contents")
            val sendIntent = Intent(this, MainActivity::class.java)

            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            sendIntent.putExtra("send", contents)
            startActivity(sendIntent)
        }

        return super.onStartCommand(intent, flags, startId)
    }
}
