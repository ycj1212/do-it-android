package com.example.samplepush

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        // 등록 id 확인을 위한 리스너 설정
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            val newToken = it.token

            println("등록 id : $newToken")
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val instanceId = FirebaseInstanceId.getInstance().id    // 등록 id 값 확인을 위한 메소드 호출하기
            println("확인된 인스턴스 id : $instanceId")
        }
    }

    override fun onNewIntent(intent: Intent?) {
        println("onNewIntent 호출됨")

        if (intent != null) {
            processIntent(intent)
        }

        super.onNewIntent(intent)
    }

    private fun processIntent(intent: Intent) {
        val from = intent.getStringExtra("from")
        if (from == null) {
            println("from is null.")
            return
        }

        val contents = intent.getStringExtra("contents")

        println("DATA : $from, $contents")
        textView.text = "[$from]로부터 수신한 데이터 : $contents"
    }

    private fun println(data: String) {
        textView2.append("$data\n")
    }
}
