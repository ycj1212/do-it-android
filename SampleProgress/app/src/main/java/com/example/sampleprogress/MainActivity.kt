package com.example.sampleprogress

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.isIndeterminate = false
        progressBar.progress = 80

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            dialog = ProgressDialog(this)
            dialog?.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            dialog?.setMessage("데이터를 확인하는 중입니다.")
            dialog?.show()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            if (dialog != null) {
                dialog?.dismiss()
            }
        }
    }
}
