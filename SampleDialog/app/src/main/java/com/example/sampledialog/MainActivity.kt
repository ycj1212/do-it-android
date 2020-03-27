package com.example.sampledialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            showMessage()
        }
    }

    private fun showMessage() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("안내")
        builder.setMessage("종료하시겠습니까?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("예") { dialog, which ->
            val message: String = "예 버튼이 눌렸습니다."
            textView?.text = message
        }
        builder.setNegativeButton("아니오") { dialog, which ->
            val message: String = "아니오 버튼이 눌렸습니다."
            textView?.text = message
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
