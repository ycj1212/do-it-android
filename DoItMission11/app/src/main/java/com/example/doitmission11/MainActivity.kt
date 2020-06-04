package com.example.doitmission11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var textview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edittext = findViewById<EditText>(R.id.edittext)
        val button = findViewById<Button>(R.id.button)
        textview = findViewById<TextView>(R.id.textview)

        button.setOnClickListener{
            val contents = edittext.text.toString()
            val intent = Intent(this, MyService::class.java)
            intent.putExtra("contents", contents)

            startService(intent)
        }

        val intent = intent
        val s = intent.getStringExtra("send")
        textview?.text = s
    }

    override fun onNewIntent(intent: Intent?) {
        val s = intent?.getStringExtra("send")
        textview?.text = s

        super.onNewIntent(intent)
    }
}
