package com.example.samplethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    lateinit var handler: MainHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val thread: BackgroundThread = BackgroundThread()
            thread.start()
        }

        handler = MainHandler()
    }

    inner class BackgroundThread : Thread() {
        var value = 0

        override fun run() {
            for (i in 0 until 100) {
                try {
                    sleep(1000)
                } catch (e: Exception) {}

                value += 1
                Log.d("Thread", "value: $value")

                val message = handler.obtainMessage()
                val bundle = Bundle()
                bundle.putInt("value", value)
                message.data = bundle

                handler.sendMessage(message)    // 핸들러로 메시지 객체 보내기
            }
        }
    }

    inner class MainHandler : Handler() {
        // 핸들러 안에서 전달받은 객체 처리하기
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val bundle = msg.data
            val value = bundle.getInt("value")
            textView?.text = "value 값: $value"
        }
    }
}
