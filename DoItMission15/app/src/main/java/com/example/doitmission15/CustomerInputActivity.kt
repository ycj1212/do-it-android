package com.example.doitmission15

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CustomerInputActivity : AppCompatActivity() {
    var editTextName: EditText? = null
    var editTextAge: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_input)

        editTextName = findViewById(R.id.editText01)
        editTextAge = findViewById(R.id.editText02)

        val birthButton: Button = findViewById(R.id.birth_button)
        birthButton.setOnClickListener {
            // 달력 알림창
        }

        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            Toast.makeText(this, "이름: ${editTextName?.text.toString()} \n나이: ${editTextAge?.text.toString()}", Toast.LENGTH_LONG).show()
            finish()
            overridePendingTransition(R.anim.translate_right_entry, R.anim.translate_right_exit)
        }
    }
}