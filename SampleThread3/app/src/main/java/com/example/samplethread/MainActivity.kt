package com.example.samplethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val thread: BackgroundThread = BackgroundThread()
            thread.start()
        }
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

                handler.post {
                    textView.text = "value 값: $value"
                }
            }
        }
    }
}
