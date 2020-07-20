package com.example.doitmission15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputButton: Button = findViewById(R.id.input_button)

        inputButton.setOnClickListener {
            val intent = Intent(this, CustomerInputActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.translate_left_entry, R.anim.translate_left_exit)
        }
    }
}