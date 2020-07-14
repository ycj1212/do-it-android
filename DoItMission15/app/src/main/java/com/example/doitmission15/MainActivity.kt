package com.example.doitmission15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    var editTextName: EditText? = null
    var editTextAge: EditText? = null
    var editTextBirth: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editText01)
        editTextAge = findViewById(R.id.editText02)
        editTextBirth = findViewById(R.id.editText03)

        val inputButton: Button = findViewById(R.id.input_button)
        val saveButton: Button = findViewById(R.id.save_button)

        val layout: FrameLayout = findViewById(R.id.layout)

        inputButton.setOnClickListener {
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_left)
            layout.startAnimation(animation)
        }

        saveButton.setOnClickListener {
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_right)
            layout.startAnimation(animation)
        }
    }
}
