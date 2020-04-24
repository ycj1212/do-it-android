package com.example.sampleparcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_CODE_MENU = 101
const val KEY_SIMPLE_DATA = "data"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = button
        button.setOnClickListener{
            val intent: Intent = Intent(applicationContext, MenuActivity::class.java)
            val data = SimpleData(100, "Hello Android!");
            intent.putExtra(KEY_SIMPLE_DATA, data)
            startActivityForResult(intent, REQUEST_CODE_MENU)
        }
    }
}
