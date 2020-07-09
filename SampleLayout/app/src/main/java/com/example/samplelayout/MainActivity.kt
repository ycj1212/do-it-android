package com.example.samplelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var layout1: Layout1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout1 = findViewById(R.id.layout1)
        layout1?.setImage(R.drawable.ic_launcher_foreground)
        layout1?.setName("김민수")
        layout1?.setMobile("010-1000-1000")

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            layout1?.setImage(R.drawable.profile1)
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            layout1?.setImage(R.drawable.profile2)
        }
    }
}
