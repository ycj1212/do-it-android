package com.example.sampledatabase

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var editText2: EditText
    lateinit var textView: TextView
    lateinit var database: SQLiteDatabase
    lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val databaseName = editText.text.toString()
            createDatabase(databaseName)
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            tableName = editText2.text.toString()
            createTable(tableName)

            insertRecord()
        }
    }

    private fun createDatabase(name: String) {
        println("createDatabase 호출됨.")

        database = openOrCreateDatabase(name, MODE_PRIVATE, null)

        println("데이터베이스 생성함: $name")
    }

    private fun createTable(name: String) {
        println("createTable 호출됨.")

        database.execSQL("create table if not exists $name( _id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)")

        println("테이블 생성함: $name")
    }

    private fun insertRecord() {
        println("insertRecord 호출됨.")

        database.execSQL("insert into $tableName(name, age, mobile) values ( 'John', 20, '010-1000-1000')")

        println("레코드 추가함.")
    }

    private fun println(data: String) {
        textView.append("$data\n")
    }
}
