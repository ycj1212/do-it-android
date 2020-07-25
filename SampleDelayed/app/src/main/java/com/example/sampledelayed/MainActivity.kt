package com.example.sampledelayed

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            request()
        }
    }

    private fun request() {
        val title = "원격 요청"
        val message = "데이터를 요청하시겠습니까?"
        val titleButtonYes = "예"
        val titleButtonNo = "아니오"
        val dialog: AlertDialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo)
        dialog.show()

        textView.text = "대화상자 표시중..."
    }

    private fun makeRequestDialog(title: CharSequence, message: CharSequence, titleButtonYes: CharSequence, titleButtonNo: CharSequence): AlertDialog {
        val requestDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        requestDialog.setTitle(title)
        requestDialog.setMessage(message)
        requestDialog.setPositiveButton(titleButtonYes) { dialogInterface: DialogInterface, i: Int ->
            textView.text = "5초 후에 결과 표시됨."
            handler.postDelayed( {  // 시간이 지난 후 코드 실행
                textView.text = "요청 완료됨."
            }, 5000)
        }

        requestDialog.setNegativeButton(titleButtonNo) { dialogInterface: DialogInterface, i: Int -> }

        return requestDialog.create()
    }
}
