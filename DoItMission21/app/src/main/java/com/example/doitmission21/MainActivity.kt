package com.example.doitmission21

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val databaseName = "d.db"
    private val tableName = "t"

    lateinit var editText: EditText
    lateinit var editText2: EditText
    lateinit var editText3: EditText
    lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edittext)      // 제목
        editText2 = findViewById(R.id.edittext2)    // 저자
        editText3 = findViewById(R.id.edittext3)    // 내용

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            database.execSQL("insert into $tableName" +
                    "(title, author, contents) " +
                    " values " +
                    "('${editText.text.toString()}', '${editText2.text.toString()}', '${editText3.text.toString()}')")
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val cursor = database.rawQuery("SELECT _id, title, author, contents from $tableName", null)

            for (i in 0 until cursor.count) {
                cursor.moveToNext()
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val author = cursor.getString(2)
                val contents = cursor.getString(3)
                println("레코드#$id $title $author $contents")
            }

            cursor.close()
        }

        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null)

        database.execSQL("CREATE TABLE IF NOT EXISTS $tableName (" +
                " _id integer PRIMARY KEY autoincrement, " +
                " title text," +
                " author text," +
                " contents text)")
    }
}
