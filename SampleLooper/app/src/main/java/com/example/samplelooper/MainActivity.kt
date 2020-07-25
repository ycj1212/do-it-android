package com.example.samplelooper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView

    val handler = Handler()

    lateinit var thread: ProcessThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val input = editText.text.toString()
            val message = Message.obtain()
            message.obj = input

            thread.processHandler.sendMessage(message)
        }

        thread = ProcessThread()
    }

    inner class ProcessThread : Thread() {
        val processHandler: ProcessHandler = ProcessHandler()

        override fun run() {
            Looper.prepare()
            Looper.loop()
        }

        inner class ProcessHandler : Handler() {
            override fun handleMessage(msg: Message) {
                val output = "$${msg.obj} from thread."

                handler.post {
                    textView.text = output
                }
            }
        }
    }
}
