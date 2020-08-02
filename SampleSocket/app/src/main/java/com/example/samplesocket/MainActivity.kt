package com.example.samplesocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var textView2: TextView

    private val handler = Handler()
    private val portNumber = 5001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val data = editText.text.toString()
            Thread(Runnable {
                send(data)
            }).start()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            Thread(Runnable {
                startServer()
            }).start()
        }
    }

    private fun printClientLog(data: String) {
        Log.d("MainActivity", data)
        handler.post {
            textView.append("$data\n")
        }
    }

    private fun printServerLog(data: String) {
        Log.d("MainActivity", data)
        handler.post {
            textView2.append("$data\n")
        }
    }

    private fun send(data: String) {
        val socket = Socket("localhost", portNumber)    // 소켓 객체 생성
        printClientLog("소켓 연결함.")

        // 소켓 객체로 데이터 보내기
        val outStream = ObjectOutputStream(socket.getOutputStream())
        outStream.writeObject(data)
        outStream.flush()
        printClientLog("데이터 전송함")

        val inStream = ObjectInputStream(socket.getInputStream())
        printClientLog("서버로부터 받음: ${inStream.readObject()}")
        socket.close()
    }

    private fun startServer() {
        val server = ServerSocket(portNumber)   // 소켓 서버 객체 생성
        printServerLog("서버 시작함: $portNumber")

        while (true) {
            // 클라이언트가 접속했을 때 만들어지는 소켓 객체 참조
            val socket = server.accept()
            val clientHost = socket.localAddress
            val clientPort = socket.port
            printServerLog("클라이언트 연결됨: $clientHost : $clientPort")

            val inStream = ObjectInputStream(socket.getInputStream())
            val obj = inStream.readObject()
            printServerLog("데이터 받음: $obj")

            val outStream = ObjectOutputStream(socket.getOutputStream())
            outStream.writeObject("$obj from Server.")
            outStream.flush()
            printServerLog("데이터 보냄.")

            socket.close()
        }
    }
}
