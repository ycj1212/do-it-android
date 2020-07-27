package com.example.sampleasynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    lateinit var task: BackgroundTask
    lateinit var value: Int
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        val button: Button = findViewById(R.id.button1)
        button.setOnClickListener {
            task = BackgroundTask()
            task.execute()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {

        }
    }
}
