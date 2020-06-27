package com.example.doitmission12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val str = intent.getStringExtra("str")
        val intent2 = Intent(context, MainActivity::class.java)

        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent2.putExtra("str", str)
        context.startActivity(intent2)
    }
}