package com.example.sampleintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", "mike")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
