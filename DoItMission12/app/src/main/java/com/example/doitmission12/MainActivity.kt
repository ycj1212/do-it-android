package com.example.doitmission12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var textview: TextView? = null
    var edittext: EditText? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittext = findViewById(R.id.edittext)
        textview = findViewById(R.id.textview)
        button = findViewById(R.id.button)

        button?.setOnClickListener {
            val str = edittext?.text.toString()
            sendToService(str)
        }

        val intent = intent
        processIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        processIntent(intent)

        super.onNewIntent(intent)
    }

    private fun sendToService(data: String) {
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("str", data)

        startService(intent)
    }

    private fun processIntent(intent: Intent?) {
        if (intent != null) {
            val str = intent.getStringExtra("str")
            textview?.text = str
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}