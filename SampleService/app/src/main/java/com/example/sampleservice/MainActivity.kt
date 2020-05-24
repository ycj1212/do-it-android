package com.example.sampleservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edittext = edittext
        val button = button
        button.setOnClickListener{
            val name = edittext.text.toString()
            val intent = Intent(applicationContext, MyService::class.java)
            intent.putExtra("command", "show")
            intent.putExtra("name", name)

            startService(intent)
        }

        // 액티비티가 새로 만들어질 때 전달된 인텐트 처리
        val passedIntent = intent
        processIntent(passedIntent)
    }

    // 액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리
    override fun onNewIntent(intent: Intent?) {
        processIntent(intent!!)

        super.onNewIntent(intent)
    }

    private fun processIntent(intent: Intent) {
        val command = intent?.getStringExtra("command")
        val name = intent?.getStringExtra("name")

        Toast.makeText(this, "command: ${command}, name: ${name}", Toast.LENGTH_LONG).show()
    }
}
