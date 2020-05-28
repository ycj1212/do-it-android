package com.example.doitmission09

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var edittext1: EditText? = null
    var edittext2: EditText? = null
    var button1: Button? = null
    var button2: Button? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittext1 = findViewById(R.id.edittext1)
        edittext2 = findViewById(R.id.edittext2)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        var y = 0
        var m = 0
        var d = 0
        val format = SimpleDateFormat("yyyy-MM-dd")
        val datePickerDialog = DatePickerDialog(this)
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth -> y = year; m = month; d = dayOfMonth
        }, 0, 0, 0)

        button1?.text = format.format(Calendar.getInstance().time)    // 오늘 날짜
        // 날짜 선택 대화상자를 띄우고 날짜를 입력받아 표시합니다.
        button1?.setOnClickListener{
            datePickerDialog.show()
            button1?.text = format.format(Date(y, m, d))
        }
        button2?.setOnClickListener{
            Toast.makeText(this, "이름: ${edittext1?.editableText.toString()}\n나이: ${edittext2?.editableText.toString()}\n생년월일: ${button1?.text}", Toast.LENGTH_LONG).show()
        }
    }
}
