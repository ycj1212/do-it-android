package com.example.samplepush

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val TAG = "FMS"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {   // 새로운 토큰을 확인했을 때 호출되는 메소드
        super.onNewToken(p0)
        Log.e(TAG, "onNewToken 호출됨: $p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) { // 새로운 메시지를 받았을 때 호출되는 메소드
        Log.d(TAG, "onMessageReceived() 호출됨.")

        val from = p0.from
        val data = p0.data
        val contents = data["contents"]

        Log.d(TAG, "from : $from, contents : $contents")
        sendToActivity(applicationContext, from!!, contents!!)
    }

    private fun sendToActivity(context: Context, from: String, contents: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("from", from)
        intent.putExtra("contents", contents)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
    }
}
