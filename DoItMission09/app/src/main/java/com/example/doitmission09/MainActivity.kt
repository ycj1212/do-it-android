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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittext1 = findViewById(R.id.edittext1)
        edittext2 = findViewById(R.id.edittext2)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        val calendar = Calendar.getInstance()
        var y = calendar.get(Calendar.YEAR)
        var m = calendar.get(Calendar.MONTH)
        var d = calendar.get(Calendar.DAY_OF_MONTH)
        val format = SimpleDateFormat("yyyy-MM-dd")
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            button1?.text = format.format(calendar.time)
        }, y, m, d)

        button1?.text = format.format(Calendar.getInstance().time)    // 오늘 날짜 표시
        // 날짜 선택 대화상자를 띄우고 날짜를 입력받아 표시
        button1?.setOnClickListener{
            datePickerDialog.show()
        }
        button2?.setOnClickListener{
            Toast.makeText(this, "이름: ${edittext1?.editableText.toString()}\n나이: ${edittext2?.editableText.toString()}\n생년월일: ${button1?.text}", Toast.LENGTH_LONG).show()
        }
    }
}
