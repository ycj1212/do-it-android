package com.example.samplecontacts

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
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
            chooseContacts()
        }
    }

    private fun chooseContacts() {
        val contactPickerIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI) // 연락처 화면을 띄우기 위한 인텐트 만들기

        startActivityForResult(contactPickerIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                val contactsUri = data?.data
                val id = contactsUri?.lastPathSegment   // 선택한 연락처의 id 값 확인하기

                getContacts(id!!)
            }
        }
    }

    private fun println(data: String) {
        textView.append("$data\n")
    }

    private fun getContacts(id: String) {
        var name: String = ""
        var cursor: Cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
            null,
            "${ContactsContract.Data.CONTACT_ID}=?",
            Array<String>(1) { id },
            null)!!

        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
            println("Name : $name")

            val columns = cursor.columnNames
            for (column in columns) {
                val index = cursor.getColumnIndex(column)
                val columnOutput = "#$index -> [$column] ${cursor.getString(index)}"
                println(columnOutput)
            }
            cursor.close()
        }
    }
}
