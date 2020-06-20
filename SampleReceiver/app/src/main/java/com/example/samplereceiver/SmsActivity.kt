package com.example.samplereceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SmsActivity : AppCompatActivity() {
    private val editText: EditText = findViewById<EditText>(R.id.edittext01)
    private val editText2: EditText = findViewById<EditText>(R.id.edittext02)
    private val editText3: EditText = findViewById<EditText>(R.id.edittext03)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            finish();
        }

        val passedIntent = intent
        processIntent(passedIntent)
    }

    override fun onNewIntent(intent: Intent) {
        processIntent(intent)

        super.onNewIntent(intent)
    }

    private fun processIntent(intent: Intent) {
        val sender = intent.getStringExtra("sender")
        val contents = intent.getStringExtra("contents")
        val receivedDate = intent.getStringExtra("receivedDate")

        editText.setText(sender)
        editText2.setText(contents)
        editText3.setText(receivedDate)
    }
}
