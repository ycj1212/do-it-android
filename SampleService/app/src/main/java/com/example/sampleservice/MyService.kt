package com.example.sampleservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

const val TAG = "MyService"

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() 호출됨")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() 호출됨")

        if (intent == null) {
            return Service.START_STICKY // 비정상 종료 -> 자동 재시작
        } else {
            processCommand(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun processCommand(intent: Intent) {
        val command = intent.getStringExtra("command")
        val name = intent.getStringExtra("name")

        Log.d(TAG, "command: ${command}, name: ${name}")

        for (i in 0 until 5) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {}

            Log.d(TAG, "Waiting $i seconds")
        }
    }
}
