package com.example.samplehttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val urlStr = editText.text.toString()
            Thread(Runnable {
                request(urlStr)
            }).start()
        }
    }

    private fun request(urlStr: String) {
        val output = StringBuilder()
        val url = URL(urlStr)
        val conn = url.openConnection() as HttpURLConnection

        conn.connectTimeout = 10000 // 연결 대기 시간(10초)
        conn.requestMethod = "GET"
        conn.doInput = true // 객체의 입력이 가능하도록 만듬

        val resCode = conn.responseCode
        val reader = BufferedReader(InputStreamReader(conn.inputStream))
        var line: String? = null
        while (true) {
            line = reader.readLine()
            if (line == null) {
                break
            }

            output.append("$line\n")
        }
        reader.close()
        conn.disconnect()

        println("응답-> $output")
    }

    private fun println(str: String) {
        handler.post {
            textView.append(str)
        }
    }
}
