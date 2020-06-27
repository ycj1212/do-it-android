package com.example.doitmission12

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            return Service.START_STICKY
        } else {
            processCommand(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun processCommand(intent: Intent) {
        val str = intent.getStringExtra("str")
        val intent2 = Intent(this, MyReceiver::class.java)

        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent2.putExtra("str", str)
        sendBroadcast(intent2)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}