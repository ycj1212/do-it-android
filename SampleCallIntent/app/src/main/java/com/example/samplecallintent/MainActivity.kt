package com.example.samplecallintent

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val data: String = editText?.text.toString()
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(data))
            startActivity(intent)
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent: Intent = Intent()
            val name: ComponentName = ComponentName("com.example.samplecallintent", "com.example.samplecallintent.MenuActivity")
            intent.component = name
            startActivityForResult(intent, 101)
        }
    }
}
