package com.example.sampleprovider

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            insertPerson()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {

        }

        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {

        }

        val button4: Button = findViewById(R.id.button4)
        button4.setOnClickListener {

        }
    }

    private fun println(data: String) {
        textView.append("$data\n")
    }

    private fun insertPerson() {
        println("insertPerson 호출됨")

        val uriString = "content://com.example.provider/person"
        var uri = Uri.Builder().build().parse(uriString)
        val cursor = contentResolver.query(uri, null, null, null, null)
        val columns = cursor?.columnNames
        println("columns count -> ${columns?.size}")

        for (i in columns!!.indices) {
            println("#$i : ${columns[i]}")
        }

        val values = ContentValues()
        values.put("name", "john")
        values.put("age", 20)
        values.put("mobile", "010-1000-1000")


        uri = contentResolver.insert(uri, values)
        println("insert 결과 -> ${uri.toString()}")
    }
}
