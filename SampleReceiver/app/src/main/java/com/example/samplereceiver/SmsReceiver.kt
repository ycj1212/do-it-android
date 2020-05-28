package com.example.samplereceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.util.*

const val TAG = "SmsReceiver"

class SmsReceiver : BroadcastReceiver() {
    // SMS를 받으면 자동으로 호출됨
    // intent: SMS 데이터
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨")

        val bundle = intent.extras  // 인텐트에서 Bundle 객체 가져오기
        val messages: Array<SmsMessage?> = parseSmsMessage(bundle!!)    // parseSmsMessage() 메서드 호출

        if (messages.size > 0) {
            val sender = messages[0]?.originatingAddress    // 발신자 번호
            Log.i(TAG, "SMS sender: ${sender}")

            val contents = messages[0]?.messageBody         // 문자 내용
            Log.i(TAG, "SMS contents: ${contents}")

            val receivedDate = Date(messages[0]!!.timestampMillis)  // SMS 받은 시각
            Log.i(TAG, "SMS received date: ${receivedDate.toString()}")
        }
    }

    private fun parseSmsMessage(bundle: Bundle): Array<SmsMessage?> {
        // 번들 객체에 들어가 있는 부가 데이터 중에서 pdus 가져오기
        val objs = bundle.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(objs.size)

        val smsCount = objs.size
        for (i in 0 until smsCount) {
            // 단말 OS 버전에 따라 다른 방식으로 메서드 호출하기
            // Build.VERSION.SDK_INT: 단말의 OS 버전 확인
            // Build.VERSION_CODES: 안드로이드 OS 버전 별 상수(M: 마시멜로)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            }
            else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }

        return messages
    }
}